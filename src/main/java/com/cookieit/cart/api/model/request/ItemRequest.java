package com.cookieit.cart.api.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ItemRequest {

    @ApiModelProperty(name = "Item name", required = true)
    @NotBlank(message = "Item name is mandatory")
    @Size(max = 100)
    private String name;

    @ApiModelProperty(name = "Count of items", required = true)
    @NotNull
    @Min(1)
    private Integer quantity;

}
