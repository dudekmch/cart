package com.cookieit.cart.domain;

import com.cookieit.cart.model.dto.CartDTO;
import com.cookieit.cart.model.exception.CartCreationException;
import com.cookieit.cart.model.exception.CartNotFoundException;

import java.util.List;

public interface CartService {

    CartDTO getCart(final Long id) throws CartNotFoundException;

    CartDTO createCart(final CartDTO cartDTO) throws CartCreationException;

    List<CartDTO> getCarts();

    void removeCart(final Long id);

    CartDTO updateCart(final Long id, final CartDTO cartDTO) throws CartNotFoundException;
}
