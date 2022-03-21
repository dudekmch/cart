package com.cookieit.cart.domain;

import com.cookieit.cart.model.dto.CartDTO;
import com.cookieit.cart.model.exception.CartNotFoundException;

import java.util.List;

public interface CartService {

    CartDTO getCart(final Long id) throws CartNotFoundException;

    Long createCart(final CartDTO cartDTO);

    List<CartDTO> getCarts();

    void removeCart(final Long id);

    void updateCart(final CartDTO cartDTO) throws CartNotFoundException;
}
