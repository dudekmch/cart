package com.cookieit.cart.domain.repository;

import com.cookieit.cart.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT DISTINCT(c) FROM Cart c LEFT JOIN FETCH c.items")
    List<Cart> getCarts();

    @Query("SELECT c FROM Cart c LEFT JOIN FETCH c.items where c.id = :id")
    Optional<Cart> getCartById(Long id);
}
