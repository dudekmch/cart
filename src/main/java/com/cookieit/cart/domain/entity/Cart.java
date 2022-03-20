package com.cookieit.cart.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
public class Cart extends AbstractEntity {

    @Column(nullable = false)
    private String shopName;

    private OffsetDateTime created;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart", cascade = {CascadeType.REMOVE})
    private List<Item> items;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
