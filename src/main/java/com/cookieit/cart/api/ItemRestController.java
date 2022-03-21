package com.cookieit.cart.api;

import com.cookieit.cart.domain.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@Api("Item")
public class ItemRestController {

    private final ItemService itemService;

    public ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @ApiOperation(value = "Remove item with id")
    @DeleteMapping(path = "/{itemId}")
    public void removeItem(@PathVariable("itemId") Long itemId) {
        itemService.removeItem(itemId);
    }

}
