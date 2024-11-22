package store.domain.store;

import java.time.LocalDateTime;
import store.domain.readers.ProductReader;
import store.domain.readers.PromotionReader;
import store.domain.store.item.Product;
import store.domain.store.items.Products;
import store.domain.store.items.Promotions;

public class ConvenienceStoreroom {
    private final Products products;
    private final Promotions promotions;

    public ConvenienceStoreroom(ProductReader productReader, PromotionReader promotionReader,
                                String productPath, String promotionPath) {

        this.promotions = new Promotions(promotionReader.read(promotionPath));
        this.products = new Products(productReader.read(productPath, promotions));
    }

    public Product findProductByNameAndDate(String name, LocalDateTime nowDate) {
        return products.findProductByNameAndDate(name, nowDate);
    }


    public boolean checkProductStock(String name, int quantity) {
        return products.isEnoughStock(name, quantity);
    }

    @Override
    public String toString() {
        return products.toString();
    }
}
