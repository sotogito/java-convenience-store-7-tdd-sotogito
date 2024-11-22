package store.service;

import camp.nextstep.edu.missionutils.DateTimes;
import java.util.List;
import java.util.Map;
import store.domain.Cart;
import store.domain.Order;
import store.domain.OrderParser;
import store.domain.store.ConvenienceStoreroom;
import store.domain.store.item.Product;

public class OrderService {
    private final ConvenienceStoreroom storeroom;
    private final Cart cart;

    public OrderService(ConvenienceStoreroom storeroom, Cart cart) {
        this.storeroom = storeroom;
        this.cart = cart;
    }

    /**
     * 등록 여부 확인 재고 차감 영수증 출력
     */

    public void registerOrder(String inputOrder) {
        Map<String, Integer> orderForm = OrderParser.parse(inputOrder);

        for (Map.Entry<String, Integer> entry : orderForm.entrySet()) {
            String name = entry.getKey();
            int quantity = entry.getValue();

            Product orderProduct = storeroom.findProductByNameAndDate(name, DateTimes.now());
            if (!storeroom.checkProductStock(name, quantity)) {
                throw new IllegalStateException("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
            }
            cart.addOrder(new Order(orderProduct, quantity));
        }
    }

    //note 현재 {상품명}은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
    public List<Order> getAddablePromotionOrder() {
        return cart.getAddablePromotionProductOrders();
    }
    /**
     * 1. 가져온 수량 - 프로모션 적용 수량 = 잔여 수량이 GET에 충족될 경우
     * 2. 프로모션 적용된 총 수량에 대해 프로모션 재고가 충분할 경우
     *
     *
     * Y : 추가한다.
     * N : 추가하지 않는다.
     */

    //note 현재 {상품명} {수량}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
    /**
     * 2. 재고가 충분하지 않을 경우
     * 3. 가져온 수량 - 적용 수량 : 적용되지 않는 수량
     *
     * Y : 적용되지 않는 수량을 일반 상품으로
     * N : 삭제
     *
     */

}
