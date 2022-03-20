package com.cookieit.cart.service;

import com.cookieit.cart.domain.service.CartService;
import com.cookieit.cart.model.dto.CartDTO;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
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
@DirtiesContext
public class CartServiceTest {

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
        List<CartDTO> cartDTOs = cartService.getCards();
        MatcherAssert.assertThat(cartDTOs.size(), Matchers.equalTo(1));
    }
}
