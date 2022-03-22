package com.cookieit.cart.domain.repository;

import com.cookieit.cart.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i where i.cart.id = :cartId")
    List<Item> findItemsByCartId(Long cartId);

}
