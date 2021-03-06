package com.cookieit.cart.domain.service;

import com.cookieit.cart.domain.ItemService;
import com.cookieit.cart.domain.entity.Cart;
import com.cookieit.cart.domain.entity.Item;
import com.cookieit.cart.domain.entity.mapper.ItemToItemDTOMapper;
import com.cookieit.cart.domain.repository.ItemRepository;
import com.cookieit.cart.model.dto.ItemDTO;
import com.cookieit.cart.model.exception.CartNotFoundException;
import com.cookieit.cart.model.exception.ItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CartServiceImpl cartService;

    public ItemServiceImpl(ItemRepository itemRepository, CartServiceImpl cartService) {
        this.itemRepository = itemRepository;
        this.cartService = cartService;
    }

    public List<ItemDTO> getItemsForCart(final Long cartId) {
        List<Item> item = itemRepository.findItemsByCartId(cartId);
        return ItemToItemDTOMapper.mapItemsToItemDTOs(item);
    }

    public ItemDTO getItem(final Long itemId) throws ItemNotFoundException {
        Item item = getItemEntity(itemId);
        return ItemToItemDTOMapper.mapItemToItemDTO(item);
    }

    public ItemDTO createItem(final ItemDTO itemDTO) throws CartNotFoundException {
        Cart cart = this.cartService.getCartEntity(itemDTO.getCartId());
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setQuantity(itemDTO.getQuantity());
        item.setCart(cart);
        return ItemToItemDTOMapper.mapItemToItemDTO(itemRepository.save(item));
    }

    public void removeItem(final Long itemId) {
        itemRepository.deleteById(itemId);
    }

    public ItemDTO updateItem(final Long itemId, final ItemDTO itemDTO) throws ItemNotFoundException {
        Item itemToSave = getItemEntity(itemId);
        itemToSave.setName(itemDTO.getName());
        itemToSave.setQuantity(itemDTO.getQuantity());
        return ItemToItemDTOMapper.mapItemToItemDTO(itemRepository.save(itemToSave));
    }

    private Item getItemEntity(final Long itemId) throws ItemNotFoundException {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Not found item with id: " + itemId));
    }
}
