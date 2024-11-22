package store.domain.store.items;

import java.util.List;
import store.domain.store.item.Product;

public class Products {
    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Product product : products) {
            builder.append(product);
            builder.append("\n");
        }
        return builder.toString();
    }

}
