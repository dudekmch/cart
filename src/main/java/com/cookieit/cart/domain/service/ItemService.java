package com.cookieit.cart.domain.service;

import com.cookieit.cart.domain.entity.Cart;
import com.cookieit.cart.domain.entity.Item;
import com.cookieit.cart.domain.repository.ItemRepository;
import com.cookieit.cart.model.dto.ItemDTO;
import com.cookieit.cart.model.exception.CartNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final CartService cartService;

    public ItemService(ItemRepository itemRepository, CartService cartService) {
        this.itemRepository = itemRepository;
        this.cartService = cartService;
    }

    public Long createItem(ItemDTO itemDTO) throws CartNotFoundException {
        Cart cart = this.cartService.getCartEntity(itemDTO.getCartId());
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setQuantity(itemDTO.getQuantity());
        item.setCart(cart);
        itemRepository.save(item);
        return item.getId();
    }


}
