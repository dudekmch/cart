package com.cookieit.cart.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class CartDTO {

    private Long id;

    private String shopName;

    private LocalDateTime created;

    private List<ItemDTO> items;
}
