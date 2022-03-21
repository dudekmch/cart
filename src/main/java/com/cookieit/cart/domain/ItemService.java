package com.cookieit.cart.domain;

import com.cookieit.cart.model.dto.ItemDTO;
import com.cookieit.cart.model.exception.CartNotFoundException;
import com.cookieit.cart.model.exception.ItemNotFoundException;

public interface ItemService {

    Long createItem(final ItemDTO itemDTO) throws CartNotFoundException;

    void removeItem(final Long itemId);

    void updateItem(final ItemDTO itemDTO) throws ItemNotFoundException;
}
