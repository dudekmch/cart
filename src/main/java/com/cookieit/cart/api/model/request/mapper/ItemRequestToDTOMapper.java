package com.cookieit.cart.api.model.request.mapper;

import com.cookieit.cart.api.model.request.ItemCreateRequest;
import com.cookieit.cart.api.model.request.ItemRequest;
import com.cookieit.cart.model.dto.ItemDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemRequestToDTOMapper {

    public static ItemDTO mapItemRequestToDTO(ItemRequest itemRequest) {
        return ItemDTO.builder()
                .quantity(itemRequest.getQuantity())
                .name(itemRequest.getName())
                .build();
    }

    public static ItemDTO mapItemCreateRequestToDTO(ItemCreateRequest itemCreateRequest) {
        return ItemDTO.builder()
                .cartId(itemCreateRequest.getCartId())
                .quantity(itemCreateRequest.getQuantity())
                .name(itemCreateRequest.getName())
                .build();
    }

}
