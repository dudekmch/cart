package com.cookieit.cart.domain.service;

import com.cookieit.cart.domain.entity.Cart;
import com.cookieit.cart.domain.entity.mapper.CartToCartDTOMapper;
import com.cookieit.cart.domain.repository.CartRepository;
import com.cookieit.cart.model.dto.CartDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Long createCart(final CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setShopName(cartDTO.getShopName());
        cartRepository.save(cart);
        return cart.getId();
    }

    public List<CartDTO> getCards() {
        List<Cart> carts = cartRepository.findAll();
        return CartToCartDTOMapper.mapCartsToCartDTOs(carts);
    }
}
