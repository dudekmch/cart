package com.cookieit.cart.api.model.response.mapper;

import com.cookieit.cart.api.model.response.CartResponse;
import com.cookieit.cart.api.model.response.ItemResponse;
import com.cookieit.cart.model.dto.CartDTO;
import com.cookieit.cart.model.dto.ItemDTO;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@UtilityClass
public class CartDTOToResponseMapper {

    public static List<CartResponse> mapCartDTOsToResponseList(final List<CartDTO> cartDTOList) {
        return cartDTOList.stream()
                .map(CartDTOToResponseMapper::mapCartDTOToResponse)
                .collect(Collectors.toList());
    }

    public static CartResponse mapCartDTOToResponse(final CartDTO cartDTO) {

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

    public static CartResponse mapItemDTOsToCartResponse(final List<ItemDTO> itemDTOList) {
        List<ItemResponse> itemResponseList = itemDTOList.stream()
                .map(ItemDTOToResponseMapper::mapItemDTOToResponse)
                .collect(Collectors.toList());

        return CartResponse.builder()
                .items(itemResponseList)
                .build();
    }
}
