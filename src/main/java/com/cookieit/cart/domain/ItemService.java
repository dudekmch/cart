package com.cookieit.cart.domain;

import com.cookieit.cart.model.dto.ItemDTO;
import com.cookieit.cart.model.exception.CartNotFoundException;
import com.cookieit.cart.model.exception.ItemNotFoundException;

import java.util.List;

public interface ItemService {

    ItemDTO getItem(Long itemId) throws ItemNotFoundException;

    List<ItemDTO> getItemsForCart(final Long cartId);

    ItemDTO createItem(final ItemDTO itemDTO) throws CartNotFoundException;

    void removeItem(final Long itemId);

    ItemDTO updateItem(final Long itemId, final ItemDTO itemDTO) throws ItemNotFoundException;
}
