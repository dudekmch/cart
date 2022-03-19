package com.cookieit.cart.domain.repository;

import com.cookieit.cart.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ItemRepository extends JpaRepository<Item, Long> {
}
