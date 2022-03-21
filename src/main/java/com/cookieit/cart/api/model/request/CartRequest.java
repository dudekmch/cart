package com.cookieit.cart.api.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CartRequest {

    @ApiModelProperty(name = "Shop name", required = true)
    @NotNull
    @Size(max = 100)
    private String shopName;

}
