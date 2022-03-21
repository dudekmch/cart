package com.cookieit.cart.api.model.response.mapper;

import com.cookieit.cart.api.model.response.CartResponse;
import com.cookieit.cart.api.model.response.ItemResponse;
import com.cookieit.cart.model.dto.CartDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CartDTOToResponseMapper {

    public static CartResponse mapCartDTOToResponse(CartDTO cartDTO){

        List<ItemResponse> itemResponseList =
                Optional.ofNullable(cartDTO.getItems())
                        .orElseGet(ArrayList::new)
                        .stream()
                        .map(ItemDTOToResponseMapper::mapItemDTOToResponse)
                        .collect(Collectors.toList());

        return CartResponse.builder()
                .id(cartDTO.getId())
                .created(cartDTO.getCreated())
                .shopName(cartDTO.getShopName())
                .items(itemResponseList)
                .build();
    }
}
