package store.domain;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<Order> generalOrder;
    private final List<Order> promotionOrder;

    public Cart() {
        generalOrder = new ArrayList<>();
        promotionOrder = new ArrayList<>();
    }


}
