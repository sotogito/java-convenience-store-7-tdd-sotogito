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

    public List<Order> getAddablePromotionProductOrders() {
        List<Order> result = new ArrayList<>();
        for (Order order : promotionOrder) {
            if (order.isCanAddablePromotionProduct()) {
                result.add(order);
            }
        }
        return result;
    }

    public List<Order> getCanNotPromotionProductOrders() {
        List<Order> result = new ArrayList<>();
        for (Order order : promotionOrder) {
            if (order.isCanNotPromotionProduct()) {
                result.add(order);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("프로모션\n");
        for (Order order : generalOrder) {
            builder.append(order.toString());
            builder.append("\n");
        }

        builder.append("일반\n");
        for (Order order : promotionOrder) {
            builder.append(order.toString());
            builder.append("\n");
        }
        return builder.toString();
    }

}
