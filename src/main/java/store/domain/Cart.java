package store.domain;

import java.util.ArrayList;
import java.util.List;
import store.constants.ProductType;

public class Cart {
    private final List<Order> generalOrder;
    private final List<Order> promotionOrder;

    public Cart() {
        generalOrder = new ArrayList<>();
        promotionOrder = new ArrayList<>();
    }

    public void addOrder(Order order) {
        if (order.getProductType().equals(ProductType.PROMOTION)) {
            promotionOrder.add(order);
            return;
        }
        generalOrder.add(order);
    }


}
