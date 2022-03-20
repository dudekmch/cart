package com.cookieit.cart.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@Getter
public class CartDTO {

    private Long id;

    private String shopName;

    private OffsetDateTime created;

    private List<ItemDTO> items;
}
