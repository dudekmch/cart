package com.cookieit.cart.domain.service;

import com.cookieit.cart.domain.entity.Cart;
import com.cookieit.cart.domain.entity.Item;
import com.cookieit.cart.domain.repository.ItemRepository;
import com.cookieit.cart.model.dto.CartDTO;
import com.cookieit.cart.model.dto.ItemDTO;
import com.cookieit.cart.model.exception.CartNotFoundException;
import com.cookieit.cart.model.exception.ItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final CartService cartService;

    public ItemService(ItemRepository itemRepository, CartService cartService) {
        this.itemRepository = itemRepository;
        this.cartService = cartService;
    }

    public Long createItem(final ItemDTO itemDTO) throws CartNotFoundException {
        Cart cart = this.cartService.getCartEntity(itemDTO.getCartId());
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setQuantity(itemDTO.getQuantity());
        item.setCart(cart);
        itemRepository.save(item);
        return item.getId();
    }

    public void removeItem(final Long itemId) {
        itemRepository.deleteById(itemId);
    }

    public void updateItem(final ItemDTO itemDTO) throws ItemNotFoundException {
        Item item = itemRepository.findById(itemDTO.getId())
                .orElseThrow(() -> new ItemNotFoundException("Not found item with id: " + itemDTO.getId()));
        item.setName(itemDTO.getName());
        item.setQuantity(itemDTO.getQuantity());
        itemRepository.save(item);
    }
}
