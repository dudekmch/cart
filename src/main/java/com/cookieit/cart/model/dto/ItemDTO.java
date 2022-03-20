package com.cookieit.cart.model.dto;

import lombok.Builder;

@Builder
public class ItemDTO {

    private Long id;

    private String name;

    private Integer quantity;

}
