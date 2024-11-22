package store.service;

import camp.nextstep.edu.missionutils.DateTimes;
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
}
