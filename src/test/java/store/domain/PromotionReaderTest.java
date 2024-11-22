package store.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;
import store.domain.readers.PromotionReader;
import store.domain.store.item.Promotion;

class PromotionReaderTest {

    @Test
    void 프로모션_상품_생성하기_확인() {
        String path = "src/main/resources/promotions.md";

        PromotionReader promotionReader = new PromotionReader();
        List<Promotion> actual = promotionReader.read(path);

        assertNotNull(actual);
    }

}