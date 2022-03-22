package com.cookieit.cart.domain.service;

import com.cookieit.cart.domain.CartService;
import com.cookieit.cart.domain.entity.Cart;
import com.cookieit.cart.domain.entity.mapper.CartToCartDTOMapper;
import com.cookieit.cart.domain.repository.CartRepository;
import com.cookieit.cart.model.dto.CartDTO;
import com.cookieit.cart.model.exception.CartNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartDTO getCart(final Long id) throws CartNotFoundException {
        Cart cart = getCartEntity(id);
        return CartToCartDTOMapper.mapCartToCartDTO(cart);
    }

    public CartDTO createCart(final CartDTO cartDTO) {
        return CartToCartDTOMapper.mapCartToCartDTO(saveNewCart(cartDTO));
    }

    public List<CartDTO> getCarts() {
        List<Cart> carts = cartRepository.getCarts();
        return CartToCartDTOMapper.mapCartsToCartDTOs(carts);
    }

    public void removeCart(final Long id) {
        cartRepository.deleteById(id);
    }

    public CartDTO updateCart(final Long id, final CartDTO cartDTO) throws CartNotFoundException {
        Cart cart = getCartEntity(id);
        cart.setShopName(cartDTO.getShopName());
        cartRepository.save(cart);
        Cart updatedCart = getCartEntity(id);
        return CartToCartDTOMapper.mapCartToCartDTO(updatedCart);
    }

    protected Cart getCartEntity(final Long id) throws CartNotFoundException {
        return cartRepository.getCartById(id)
                .orElseThrow(() -> new CartNotFoundException("Not found cart with id: " + id));
    }

    private Cart saveNewCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setShopName(cartDTO.getShopName());
        return cartRepository.save(cart);
    }
}
