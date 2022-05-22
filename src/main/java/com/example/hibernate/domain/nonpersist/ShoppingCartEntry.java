package com.example.hibernate.domain.nonpersist;


import com.example.hibernate.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class ShoppingCartEntry {
    private Long productId;
    private String productTitle;

    private int count = 0;
    private BigDecimal cost;

    public ShoppingCartEntry addProduct(Product product) {
        if (productId == null) {
            productId = product.getId();
            productTitle = product.getTitle();
            cost = product.getCost();
        }
        count++;
        return this;
    }

    public boolean decreaseAndIsOver(int count) {
        this.count -= count;
        return this.count < 1;
    }

    public boolean decreaseAndIsOver() {
        this.count -= 1;
        return this.count < 1;
    }

    public ShoppingCartEntry(ShoppingCartEntry that) {
        productId = that.getProductId();
        productTitle = that.getProductTitle();
        count = that.getCount();
        cost = that.getCost();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartEntry that = (ShoppingCartEntry) o;
        return Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId());
    }
}
