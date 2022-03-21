package com.cookieit.cart.domain.entity.mapper;

import com.cookieit.cart.domain.entity.Cart;
import com.cookieit.cart.model.dto.CartDTO;
import com.cookieit.cart.model.dto.ItemDTO;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CartToCartDTOMapper {

    public static List<CartDTO> mapCartsToCartDTOs(List<Cart> carts) {
        return carts.stream()
                .map(CartToCartDTOMapper::mapCartToCartDTO)
                .collect(Collectors.toList());
    }

    public static CartDTO mapCartToCartDTO(Cart cart) {
        List<ItemDTO> itemDTOs = Optional.ofNullable(cart.getItems())
                .map(ItemToItemDTOMapper::mapItemsToItemDTOs)
                .orElseGet(ArrayList::new);

        return CartDTO.builder()
                .id(cart.getId())
                .shopName(cart.getShopName())
                .created(cart.getCreated())
                .items(itemDTOs)
                .build();
    }
}
