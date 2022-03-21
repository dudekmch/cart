package com.cookieit.cart.api;

import com.cookieit.cart.api.model.mapper.CartRequestToDTOMapper;
import com.cookieit.cart.api.model.request.CartRequest;
import com.cookieit.cart.domain.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
@Api("Cart")
public class CartRestController {

    private final CartService cartService;

    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }

    @ApiOperation(value = "Create new cart")
    @PostMapping
    public Long createCart(@Valid @RequestBody CartRequest cartRequest) {
        return cartService.createCart(CartRequestToDTOMapper.mapCartRequestToDTO(cartRequest));
    }
}
