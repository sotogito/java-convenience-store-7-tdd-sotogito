package store.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import camp.nextstep.edu.missionutils.DateTimes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.constants.ResourcePath;
import store.domain.readers.ProductReader;
import store.domain.readers.PromotionReader;
import store.domain.store.ConvenienceStoreroom;

class OrderTest {
    private ConvenienceStoreroom storeroom = new ConvenienceStoreroom(
            new ProductReader(), new PromotionReader(),
            ResourcePath.PRODUCT.get(), ResourcePath.PROMOTION.get());

    @Test
    void isCanAddablePromotionProduct() {
        Order order = new Order(storeroom.findProductByNameAndDate("콜라", DateTimes.now()), 8);
        assertTrue(order.isCanAddablePromotionProduct());
    }

    @Test
    void getAddablePromotionCount_프로모션_상품_추가_가능_시_추가_수량_확인() {
        Order order = new Order(storeroom.findProductByNameAndDate("콜라", DateTimes.now()), 8);
        assertEquals(1, order.getAddablePromotionCount());
    }

    @DisplayName("잔여 수량이 기본 Buy 수량이 미치지 못하는 경우")
    @Test
    void getAddablePromotionCount_프로모션_상품_추가_불가능_시_추가_수량_확인() {
        Order order = new Order(storeroom.findProductByNameAndDate("콜라", DateTimes.now()), 7);
        assertEquals(0, order.getAddablePromotionCount());
    }

    @Test
    void isCanNotPromotionProduct() {
        Order order = new Order(storeroom.findProductByNameAndDate("콜라", DateTimes.now()), 100);
        assertTrue(order.isCanNotPromotionProduct());
    }

    @DisplayName("전체 재고가 프로모션 상품 추가 후 수량보다 부족할 경우")
    @Test
    void getWithoutPromotionCount_프로모션_추가_후() {
        Order order = new Order(storeroom.findProductByNameAndDate("초코바", DateTimes.now()), 5);
        assertEquals(1, order.getWithoutPromotionCount());
    }

    @DisplayName("프로모션 재고 이상의 수량일 경우")
    @Test
    void isCanNotPromotionProduct_프로모션_재고_이상_원쁠원() {
        Order order = new Order(storeroom.findProductByNameAndDate("초코바", DateTimes.now()), 7);
        assertEquals(3, order.getWithoutPromotionCount());
    }

    @Test
    void isCanNotPromotionProduct_프로모션_재고_이상_투쁠원() {
        Order order = new Order(storeroom.findProductByNameAndDate("콜라", DateTimes.now()), 15);
        assertEquals(6, order.getWithoutPromotionCount());
    }

}