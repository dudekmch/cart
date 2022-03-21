package com.cookieit.cart.domain.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cart extends AbstractEntity {

    @Column(nullable = false, length = 100)
    private String shopName;

    @Column
    @CreationTimestamp
    private LocalDateTime created;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart", cascade = {CascadeType.REMOVE})
    private List<Item> items;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
