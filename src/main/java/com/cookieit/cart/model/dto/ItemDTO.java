package com.cookieit.cart.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemDTO {

    private Long id;

    private String name;

    private Integer quantity;

    private Long cartId;

}
