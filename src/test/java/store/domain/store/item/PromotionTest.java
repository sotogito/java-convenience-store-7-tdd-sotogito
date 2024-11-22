package store.domain.store.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import camp.nextstep.edu.missionutils.DateTimes;
import org.junit.jupiter.api.Test;

class PromotionTest {
    private Promotion promotion = new Promotion("탄산2+1", 2, 1, DateTimes.now(), DateTimes.now());

    @Test
    void calculateTotalCountCanPromotion_프로모션_모두_적용() {
        assertEquals(9, promotion.calculatePromotionCount(9));
    }

    @Test
    void calculateTotalCountCanPromotion_적용되지_않는_상품_개수_반환() {
        assertEquals(6, promotion.calculatePromotionCount(8));
    }

}