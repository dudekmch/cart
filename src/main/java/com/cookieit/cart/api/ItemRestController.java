package com.cookieit.cart.api;

import com.cookieit.cart.api.model.request.ItemCreateRequest;
import com.cookieit.cart.api.model.request.ItemRequest;
import com.cookieit.cart.api.model.request.mapper.ItemRequestToDTOMapper;
import com.cookieit.cart.api.model.response.ItemResponse;
import com.cookieit.cart.api.model.response.mapper.ItemDTOToResponseMapper;
import com.cookieit.cart.domain.ItemService;
import com.cookieit.cart.model.dto.ItemDTO;
import com.cookieit.cart.model.exception.CartNotFoundException;
import com.cookieit.cart.model.exception.ItemNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/item")
@Api("Item")
public class ItemRestController {

    private final ItemService itemService;

    public ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @ApiOperation(value = "Create new item")
    @PostMapping(path = "")
    public ItemResponse createItem(@Valid @RequestBody ItemCreateRequest itemCreateRequest) {
        try {
            ItemDTO createdItemDTO =
                    itemService.createItem(ItemRequestToDTOMapper.mapItemCreateRequestToDTO(itemCreateRequest));
            return ItemDTOToResponseMapper.mapItemDTOToResponse(createdItemDTO);
        } catch (CartNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cart Not Found", ex);
        }
    }

    @ApiOperation(value = "Remove item with id")
    @DeleteMapping(path = "/{itemId}")
    public void removeItem(@PathVariable("itemId") Long itemId) {
        itemService.removeItem(itemId);
    }

    @ApiOperation(value = "Patch item")
    @PatchMapping(path = "/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ItemResponse patchItem(@PathVariable("itemId") Long itemId, @Valid @RequestBody ItemRequest itemRequest) {
        try {
            ItemDTO patchedItemDTO = itemService.updateItem(itemId, ItemRequestToDTOMapper.mapItemRequestToDTO(itemRequest));
            return ItemDTOToResponseMapper.mapItemDTOToResponse(patchedItemDTO);
        } catch (ItemNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Item Not Found", ex);
        }
    }

}
