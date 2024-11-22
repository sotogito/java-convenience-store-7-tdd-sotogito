package store.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;
import store.domain.item.Product;
import store.domain.item.Promotion;

class ProductReaderTest {

    @Test
    void 상품_등록_확인() {
        ProductReader reader = new ProductReader();
        PromotionReader promotionReader = new PromotionReader();
        List<Promotion> promotionList = promotionReader.read("src/main/resources/promotions.md");
        Promotions promotions = new Promotions(promotionList);

        String path = "src/main/resources/products.md";

        List<Product> actual = reader.read(path, promotions);

        assertNotNull(actual);
    }

}