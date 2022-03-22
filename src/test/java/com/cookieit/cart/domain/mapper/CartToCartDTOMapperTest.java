package com.cookieit.cart.domain.mapper;

import com.cookieit.cart.domain.entity.Cart;
import com.cookieit.cart.domain.entity.Item;
import com.cookieit.cart.domain.entity.mapper.CartToCartDTOMapper;
import com.cookieit.cart.model.dto.CartDTO;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.cookieit.cart.TestConstant.CART_ID;
import static com.cookieit.cart.TestConstant.CART_SHOP_NAME;

class CartToCartDTOMapperTest {

    private final Cart testCart = new Cart();
    private final Item itemOne = new Item();
    private final Item itemTwo = new Item();
    private final List<Item> items = Arrays.asList(itemOne, itemTwo);

    @BeforeEach
    public void setUp() {
        setupCart();
    }

    @Test
    void shouldCorrectMapCartEntityWithItemsToDTO() {
        testCart.setItems(items);
        CartDTO cartDTO = CartToCartDTOMapper.mapCartToCartDTO(testCart);
        MatcherAssert.assertThat(cartDTO.getId(), Matchers.equalTo(CART_ID));
        MatcherAssert.assertThat(cartDTO.getShopName(), Matchers.equalTo(CART_SHOP_NAME));
        MatcherAssert.assertThat(cartDTO.getItems().size(), Matchers.equalTo(items.size()));
    }

    @Test
    void shouldReturnCartDTOWithEmptyItemsListWhenItemsListIsNull() {
        testCart.setItems(null);
        CartDTO cartDTO = CartToCartDTOMapper.mapCartToCartDTO(testCart);
        MatcherAssert.assertThat(cartDTO.getItems().size(), Matchers.equalTo(0));
    }

    private void setupCart() {
        testCart.setId(CART_ID);
        testCart.setShopName(CART_SHOP_NAME);
    }

}
