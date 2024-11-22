package store.service;

import camp.nextstep.edu.missionutils.DateTimes;
import java.util.List;
import java.util.Map;
import store.constants.WhetherAnswer;
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
        System.out.println(cart);
    }

    //note 현재 {상품명}은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
    public List<Order> getAddablePromotionOrder() {
        return cart.getAddablePromotionProductOrders();
    }

    public void handleAddablePromotionOrder(WhetherAnswer answer, Order order, int addableQuantity) {
        if (answer.isYes()) {
            order.addPurchaseQuantity(addableQuantity);
        }
    }

    //note 현재 {상품명} {수량}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
    public List<Order> getCanNotPromotionOrders() {
        return cart.getCanNotPromotionProductOrders();
    }

    public void handleCanNotPromotionOrder(WhetherAnswer answer, Order order, int canNotPromotionQuantity) {
        if (answer.isYes()) {
            //note 일반 상품으로
            order.deletePurchaseQuantity(canNotPromotionQuantity);
            cart.addOrder(order.makeNewGeneralProductOrder(canNotPromotionQuantity, storeroom));
            return;
        }
        order.deletePurchaseQuantity(canNotPromotionQuantity);
    }
    /**
     * 2. 재고가 충분하지 않을 경우
     * 3. 가져온 수량 - 적용 수량 : 적용되지 않는 수량
     *
     * Y : 적용되지 않는 수량을 일반 상품으로
     * N : 삭제
     *
     */

}
