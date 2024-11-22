package store.domain.store;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import store.constants.ProductType;
import store.constants.ResourcePath;
import store.domain.readers.ProductReader;
import store.domain.readers.PromotionReader;
import store.domain.store.item.Product;

class ConvenienceStoreroomTest {
    private ConvenienceStoreroom storeroom = new ConvenienceStoreroom(
            new ProductReader(), new PromotionReader(),
            ResourcePath.PRODUCT.get(), ResourcePath.PROMOTION.get());


    @Test
    void findProductByNameAndDate_프로모션_상품() {
        Product product = storeroom.findProductByNameAndDate("콜라", DateTimes.now());
        assertTrue(product.getType().equals(ProductType.PROMOTION));
    }

    @Test
    void findProductByNameAndDate_기간_불충족_프로모션_상품() {
        Product product = storeroom.findProductByNameAndDate("감자칩",
                LocalDate.of(2024, 2, 1).atStartOfDay());
        assertTrue(product.getType().equals(ProductType.GENERAL));
    }

    @Test
    void checkProductStock_프로모션_상품_재고_부족() {
        assertFalse(storeroom.checkProductStock("감자칩", 100));
    }

    @Test
    void checkProductStock_일반_상품_재고_부족() {
        assertFalse(storeroom.checkProductStock("에너지바", 6));
    }


}