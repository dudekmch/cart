package com.cookieit.cart.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(schema = "cart")
public class Cart extends AbstractEntity {

    @Column(nullable = false)
    private String shopName;

    @Column
    private OffsetDateTime created;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart", cascade = {CascadeType.REMOVE})
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
