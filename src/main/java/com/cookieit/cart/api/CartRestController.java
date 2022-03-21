package com.cookieit.cart.api;

import com.cookieit.cart.domain.CartService;
import com.cookieit.cart.model.dto.CartDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Long createCart(CartDTO cartDTO) {
        return cartService.createCart(cartDTO);
    }
}
