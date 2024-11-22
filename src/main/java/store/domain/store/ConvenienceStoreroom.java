package store.domain.store;

import store.domain.readers.ProductReader;
import store.domain.readers.PromotionReader;
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

    @Override
    public String toString() {
        return products.toString();
    }
}
