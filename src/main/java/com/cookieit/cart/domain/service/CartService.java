package com.cookieit.cart.domain.service;

import com.cookieit.cart.domain.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
}
