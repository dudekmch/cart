package com.cookieit.cart.domain.repository;

import com.cookieit.cart.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {
}
