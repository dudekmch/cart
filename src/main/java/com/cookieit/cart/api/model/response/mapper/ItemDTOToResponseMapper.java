package com.cookieit.cart.api.model.response.mapper;

import com.cookieit.cart.api.model.response.ItemResponse;
import com.cookieit.cart.model.dto.ItemDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemDTOToResponseMapper {

    public static ItemResponse mapItemDTOToResponse(ItemDTO itemDTO) {
        return ItemResponse.builder()
                .id(itemDTO.getId())
                .name(itemDTO.getName())
                .quantity(itemDTO.getQuantity())
                .build();
    }
}
