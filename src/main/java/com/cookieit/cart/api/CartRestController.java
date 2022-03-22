package com.cookieit.cart.api;

import com.cookieit.cart.api.model.request.CartRequest;
import com.cookieit.cart.api.model.request.mapper.CartRequestToDTOMapper;
import com.cookieit.cart.api.model.response.CartResponse;
import com.cookieit.cart.api.model.response.mapper.CartDTOToResponseMapper;
import com.cookieit.cart.domain.CartService;
import com.cookieit.cart.model.dto.CartDTO;
import com.cookieit.cart.model.exception.CartCreationException;
import com.cookieit.cart.model.exception.CartNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
@Api("Cart")
public class CartRestController {

    private final CartService cartService;

    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }

    @ApiOperation(value = "Get all carts")
    @GetMapping(path = "carts")
    public List<CartResponse> getCarts() {
        List<CartDTO> cartDTOList = cartService.getCarts();
        return CartDTOToResponseMapper.mapCartDTOsToResponseList(cartDTOList);
    }

    @ApiOperation(value = "Get cart by id")
    @GetMapping(path = "cart/{cartId}")
    public CartResponse getCart(@PathVariable("cartId") Long cartId) {
        try {
            CartDTO cartDto = cartService.getCart(cartId);
            return CartDTOToResponseMapper.mapCartDTOToResponse(cartDto);
        } catch (CartNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cart Not Found", ex);
        }
    }

    @ApiOperation(value = "Create new cart")
    @PostMapping(path = "cart", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartResponse createCart(@Valid @RequestBody CartRequest cartRequest) {
        try {
            CartDTO cartDTO = cartService.createCart(CartRequestToDTOMapper.mapCartRequestToDTO(cartRequest));
            return CartDTOToResponseMapper.mapCartDTOToResponse(cartDTO);
        } catch(CartCreationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error during creating cart", ex);
        }
    }

    @ApiOperation(value = "Delete cart")
    @DeleteMapping (path = "cart/{cartId}")
    public void deleteCart(@PathVariable("cartId") Long cartId) {
        cartService.removeCart(cartId);
    }

    @ApiOperation(value = "Put cart")
    @PutMapping (path = "cart/{cartId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartResponse putCart(@PathVariable("cartId") Long cartId, @Valid @RequestBody CartRequest cartRequest) {
        try {
            CartDTO cartDTO = cartService.updateCart(cartId, CartRequestToDTOMapper.mapCartRequestToDTO(cartRequest));
            return CartDTOToResponseMapper.mapCartDTOToResponse(cartDTO);
        } catch (CartNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cart Not Found", ex);
        }
    }
}
