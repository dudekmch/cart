package com.cookieit.cart.api.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemCreateRequest extends ItemRequest {

    @ApiModelProperty(name = "Cart id", required = true)
    @NotNull
    private Long cartId;
}
