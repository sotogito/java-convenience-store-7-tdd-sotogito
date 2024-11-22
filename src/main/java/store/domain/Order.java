package store.domain;

import store.constants.ProductType;
import store.domain.store.item.Product;

public class Order {
    private final Product product;
    private final int quantity;

    public Order(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductType getProductType() {
        return product.getType();
    }

}
