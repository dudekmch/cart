package com.cookieit.cart.domain;

import com.cookieit.cart.model.dto.ItemDTO;
import com.cookieit.cart.model.exception.CartNotFoundException;
import com.cookieit.cart.model.exception.ItemNotFoundException;

import java.util.List;

public interface ItemService {

    ItemDTO getItem(Long itemId) throws ItemNotFoundException;

    List<ItemDTO> getItemsForCart(final Long cartId);

    Long createItem(final ItemDTO itemDTO) throws CartNotFoundException;

    void removeItem(final Long itemId);

    void updateItem(final ItemDTO itemDTO) throws ItemNotFoundException;
}
