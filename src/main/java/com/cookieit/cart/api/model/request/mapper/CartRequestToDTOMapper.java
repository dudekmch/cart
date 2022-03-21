package com.cookieit.cart.api.model.request.mapper;

import com.cookieit.cart.api.model.request.CartRequest;
import com.cookieit.cart.model.dto.CartDTO;

public class CartRequestToDTOMapper {

    public static CartDTO mapCartRequestToDTO(CartRequest request) {
        return CartDTO.builder()
                .shopName(request.getShopName())
                .build();
    }
}
