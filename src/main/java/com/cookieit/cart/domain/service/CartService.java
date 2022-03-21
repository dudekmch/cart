package com.cookieit.cart.domain.service;

import com.cookieit.cart.domain.entity.Cart;
import com.cookieit.cart.domain.entity.mapper.CartToCartDTOMapper;
import com.cookieit.cart.domain.repository.CartRepository;
import com.cookieit.cart.model.dto.CartDTO;
import com.cookieit.cart.model.exception.CartNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartDTO getCart(final Long id) throws CartNotFoundException {
        Cart cart = getCartEntity(id);
        return CartToCartDTOMapper.mapCartToCartDTO(cart);
    }

    public Long createCart(final CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setShopName(cartDTO.getShopName());
        cartRepository.save(cart);
        return cart.getId();
    }

    public List<CartDTO> getCarts() {
        List<Cart> carts = cartRepository.findAll();
        return CartToCartDTOMapper.mapCartsToCartDTOs(carts);
    }

    public void removeCart(final Long id) {
        cartRepository.deleteById(id);
    }

    public void updateCart(final CartDTO cartDTO) throws CartNotFoundException {
        Cart cart = getCartEntity(cartDTO.getId());

        cart.setShopName(cartDTO.getShopName());
        cartRepository.save(cart);
    }

    protected Cart getCartEntity(final Long id) throws CartNotFoundException {
        return cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException("Not found cart with id: " + id));
    }
}
