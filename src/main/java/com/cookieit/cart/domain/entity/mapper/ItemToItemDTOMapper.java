package com.cookieit.cart.domain.entity.mapper;

import com.cookieit.cart.domain.entity.Item;
import com.cookieit.cart.model.dto.ItemDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ItemToItemDTOMapper {

    public static List<ItemDTO> mapItemsToItemDTOs(List<Item> items) {
        return items.stream()
                .map(ItemToItemDTOMapper::mapItemToItemDTO)
                .collect(Collectors.toList());
    }

    public static ItemDTO mapItemToItemDTO(Item item){
        return ItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .quantity(item.getQuantity())
                .build();
    }
}
