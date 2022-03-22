package com.cookieit.cart.api.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemResponse {

    private Long id;

    private String name;

    private Integer quantity;

}
