package com.cookieit.cart.service;

import com.cookieit.cart.domain.CartService;
import com.cookieit.cart.model.dto.CartDTO;
import com.cookieit.cart.model.exception.CartCreationException;
import com.cookieit.cart.model.exception.CartNotFoundException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

import static com.cookieit.cart.TestConstant.CART_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.cookieit.cart.TestConstant.CART_SHOP_NAME;
import static com.cookieit.cart.TestConstant.IMPORT_DATA_CART_ID;
import static com.cookieit.cart.TestConstant.NOT_EXIST_CART_ID;


@SpringBootTest
@EnableTransactionManagement
@TestPropertySource("/utest.properties")
class CartServiceTest {

    private static final Integer IMPORT_DATA_CARTS_NUMBER = 1;
    private static final String CART_SHOP_NAME_NEW_VALUE = "updatedShopName";

    @Autowired
    private CartService cartService;

    @Test
    void shouldReturnCart() throws CartNotFoundException {
        CartDTO cartDTO = cartService.getCart(IMPORT_DATA_CART_ID);
        MatcherAssert.assertThat(cartDTO.getId(), Matchers.equalTo(IMPORT_DATA_CART_ID));
    }

    @Test
    void shouldThrowCartNotFoundExceptionWhenCartWithIdNotExist() {
        CartNotFoundException thrown =
                Assertions.assertThrows(CartNotFoundException.class, () -> cartService.getCart(NOT_EXIST_CART_ID));
        MatcherAssert.assertThat(thrown.getMessage(), Matchers.equalTo(CART_NOT_FOUND_EXCEPTION_MESSAGE + NOT_EXIST_CART_ID));
    }

    @Test
    void shouldReturnAllCarts() {
        List<CartDTO> cartDTOs = cartService.getCarts();
        MatcherAssert.assertThat(cartDTOs.size(), Matchers.equalTo(IMPORT_DATA_CARTS_NUMBER));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldCreateCartAndReturnId() throws CartCreationException {
        CartDTO cartToSave = CartDTO.builder()
                .shopName(CART_SHOP_NAME)
                .build();
        CartDTO newCart = cartService.createCart(cartToSave);
        MatcherAssert.assertThat(newCart.getId(), Matchers.equalTo(2L));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldRemoveCart() {
        cartService.removeCart(IMPORT_DATA_CART_ID);
        List<CartDTO> cartDTOs = cartService.getCarts();
        MatcherAssert.assertThat(cartDTOs.size(), Matchers.equalTo(IMPORT_DATA_CARTS_NUMBER - 1));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldUpdateShopNameInCart() throws CartNotFoundException {
        CartDTO cartDTO = CartDTO.builder()
                .shopName(CART_SHOP_NAME_NEW_VALUE)
                .build();
        CartDTO updatedCartDTO = cartService.updateCart(IMPORT_DATA_CART_ID, cartDTO);
        MatcherAssert.assertThat(updatedCartDTO.getShopName(), Matchers.equalTo(CART_SHOP_NAME_NEW_VALUE));
    }

    @Test
    void shouldThrowCartNotFoundExceptionWhenCartToUpdateNotExist() {
        CartDTO cartDTO = CartDTO.builder()
                .shopName(CART_SHOP_NAME_NEW_VALUE)
                .build();
        CartNotFoundException thrown =
                Assertions.assertThrows(CartNotFoundException.class, () -> cartService.updateCart(NOT_EXIST_CART_ID, cartDTO));
        MatcherAssert.assertThat(thrown.getMessage(), Matchers.equalTo(CART_NOT_FOUND_EXCEPTION_MESSAGE + NOT_EXIST_CART_ID));
    }
}
