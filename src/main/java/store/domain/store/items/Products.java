package store.domain.store.items;

import java.time.LocalDateTime;
import java.util.List;
import store.domain.store.item.Product;
import store.domain.store.item.PromotionProduct;

public class Products {
    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public Product findProductByNameAndDate(String name, LocalDateTime nowDate) {
        for (Product product : products) {
            if (product.isSameName(name)) {
                if (product instanceof PromotionProduct promotionProduct) {
                    if (promotionProduct.isValidDate(nowDate)) {
                        return promotionProduct;
                    }
                    continue;
                }
                return product;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 상품입니다. 다시 입력해 주세요.");
    }

    public boolean isEnoughStock(String name, int quantity) {
        int totalStock = 0;

        for (Product product : products) {
            if (product.isSameName(name)) {
                totalStock += product.getQuantity();
            }
        }
        return totalStock >= quantity;
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
