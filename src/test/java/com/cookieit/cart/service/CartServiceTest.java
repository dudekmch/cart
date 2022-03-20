package com.cookieit.cart.service;

import com.cookieit.cart.domain.service.CartService;
import com.cookieit.cart.model.dto.CartDTO;
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

import static com.cookieit.cart.TestConstant.CART_SHOP_NAME;


@SpringBootTest
@EnableTransactionManagement
@TestPropertySource("/utest.properties")
public class CartServiceTest {

    private static final Long IMPORT_DATA_CART_ID = 1L;
    private static final Integer IMPORT_DATA_CARTS_NUMBER = 1;

    @Autowired
    private CartService cartService;

    @Test
    public void shouldCreateCartAndReturnIdSavedCart() {
        CartDTO cartToSave = CartDTO.builder()
                .shopName(CART_SHOP_NAME)
                .build();
        Long savedCartId = cartService.createCart(cartToSave);
        MatcherAssert.assertThat(savedCartId, Matchers.equalTo(2L));
    }

    @Test
    public void shouldReturnAllCarts() {
        List<CartDTO> cartDTOs = cartService.getCarts();
        MatcherAssert.assertThat(cartDTOs.size(), Matchers.equalTo(IMPORT_DATA_CARTS_NUMBER));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void shouldRemoveCart() {
        cartService.removeCart(IMPORT_DATA_CART_ID);
        List<CartDTO> cartDTOs = cartService.getCarts();
        MatcherAssert.assertThat(cartDTOs.size(), Matchers.equalTo(IMPORT_DATA_CARTS_NUMBER - 1));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void shouldUpdateShopNameInCart() throws CartNotFoundException {
        final String updatedShopName = "updatedShopName";
        CartDTO cartDTO = CartDTO.builder()
                .id(IMPORT_DATA_CART_ID)
                .shopName(updatedShopName)
                .build();
        cartService.updateCart(cartDTO);
        List<CartDTO> cartDTOs = cartService.getCarts();
        MatcherAssert.assertThat(cartDTOs.size(), Matchers.equalTo(IMPORT_DATA_CARTS_NUMBER));
        MatcherAssert.assertThat(cartDTOs.get(0).getShopName(), Matchers.equalTo(updatedShopName));
    }

    @Test
    public void shouldThrowCartNotFoundExceptionWhenCartWithIdNotExist() {
        final String updatedShopName = "updatedShopName";
        final Long notExistCartId = IMPORT_DATA_CART_ID + 1;
        CartDTO cartDTO = CartDTO.builder()
                .id(notExistCartId)
                .shopName(updatedShopName)
                .build();
        CartNotFoundException thrown =
                Assertions.assertThrows(CartNotFoundException.class, () -> cartService.updateCart(cartDTO));
        MatcherAssert.assertThat(thrown.getMessage(), Matchers.equalTo("Not found cart with id: " + notExistCartId));
    }
}
