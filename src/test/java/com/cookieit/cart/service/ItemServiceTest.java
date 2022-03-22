package com.cookieit.cart.service;

import com.cookieit.cart.domain.ItemService;
import com.cookieit.cart.domain.entity.Item;
import com.cookieit.cart.domain.repository.ItemRepository;
import com.cookieit.cart.model.dto.ItemDTO;
import com.cookieit.cart.model.exception.CartNotFoundException;
import com.cookieit.cart.model.exception.ItemNotFoundException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

import static com.cookieit.cart.TestConstant.CART_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.cookieit.cart.TestConstant.IMPORT_DATA_CART_ID;
import static com.cookieit.cart.TestConstant.NOT_EXIST_CART_ID;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@EnableTransactionManagement
@TestPropertySource("/utest.properties")
public class ItemServiceTest {

    private static final Integer NEW_ITEM_QUANTITY = 5;
    private static final Long IMPORT_DATA_ITEM_ID_1 = 1L;
    private static final Long NOT_EXIST_ITEM_ID = 123L;
    private static final Integer IMPORT_DATA_ITEM_COUNT = 2;
    private static final String NEW_ITEM_NAME = "newItemName";
    public static final String ITEM_NOT_FOUND_EXCEPTION_MESSAGE = "Not found item with id: ";

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void shouldReturnItem() throws ItemNotFoundException {
        ItemDTO itemDTO = itemService.getItem(IMPORT_DATA_ITEM_ID_1);
        MatcherAssert.assertThat(itemDTO.getId(), Matchers.equalTo(IMPORT_DATA_ITEM_ID_1));
    }

    @Test
    public void shouldThrowItemNotFoundExceptionWhenItemWithIdNotExist() {
        ItemNotFoundException thrown =
                Assertions.assertThrows(ItemNotFoundException.class, () -> itemService.getItem(NOT_EXIST_ITEM_ID));
        MatcherAssert.assertThat(thrown.getMessage(), Matchers.equalTo(ITEM_NOT_FOUND_EXCEPTION_MESSAGE + NOT_EXIST_ITEM_ID));
    }

    @Test
    public void shouldReturnItemsForCartId() {
        List<ItemDTO> itemDTOList = itemService.getItemsForCart(IMPORT_DATA_CART_ID);
        MatcherAssert.assertThat(itemDTOList.size(), Matchers.equalTo(IMPORT_DATA_ITEM_COUNT));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void shouldCreateItemAndReturnId() throws CartNotFoundException {
        ItemDTO itemDTO = ItemDTO.builder()
                .name(NEW_ITEM_NAME)
                .quantity(NEW_ITEM_QUANTITY)
                .cartId(IMPORT_DATA_CART_ID)
                .build();
        Long savedItemId = itemService.createItem(itemDTO);
        MatcherAssert.assertThat(savedItemId, Matchers.equalTo(3L));
    }

    @Test
    public void shouldThrowCartNotFoundExceptionWhenCartWithIdNotExist() {
        ItemDTO itemDTO = ItemDTO.builder()
                .name(NEW_ITEM_NAME)
                .quantity(NEW_ITEM_QUANTITY)
                .cartId(NOT_EXIST_CART_ID)
                .build();
        CartNotFoundException thrown =
                Assertions.assertThrows(CartNotFoundException.class, () -> itemService.createItem(itemDTO));
        MatcherAssert.assertThat(thrown.getMessage(), Matchers.equalTo(CART_NOT_FOUND_EXCEPTION_MESSAGE + NOT_EXIST_CART_ID));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void shouldRemoveItem() {
        itemService.removeItem(IMPORT_DATA_ITEM_ID_1);
        long countLeftItems = itemRepository.count();
        MatcherAssert.assertThat(countLeftItems, Matchers.equalTo(1L));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void shouldUpdateItem() throws ItemNotFoundException {
        ItemDTO itemDTO = ItemDTO.builder()
                .id(IMPORT_DATA_ITEM_ID_1)
                .name(NEW_ITEM_NAME)
                .quantity(NEW_ITEM_QUANTITY)
                .build();
        itemService.updateItem(itemDTO);
        Item item = itemRepository.findById(IMPORT_DATA_ITEM_ID_1)
                .orElse(null);
        MatcherAssert.assertThat(item, notNullValue());
        MatcherAssert.assertThat(item.getName(), Matchers.equalTo(NEW_ITEM_NAME));
        MatcherAssert.assertThat(item.getQuantity(), Matchers.equalTo(NEW_ITEM_QUANTITY));
    }

    @Test
    public void shouldThrowItemNotFoundExceptionWhenItemToUpdateNotExist() {
        ItemDTO itemDTO = ItemDTO.builder()
                .id(NOT_EXIST_ITEM_ID)
                .build();
        ItemNotFoundException thrown =
                Assertions.assertThrows(ItemNotFoundException.class, () -> itemService.updateItem(itemDTO));
        MatcherAssert.assertThat(thrown.getMessage(), Matchers.equalTo(ITEM_NOT_FOUND_EXCEPTION_MESSAGE + NOT_EXIST_ITEM_ID));
    }
}
