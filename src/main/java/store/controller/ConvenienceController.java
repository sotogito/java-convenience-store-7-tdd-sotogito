package store.controller;

import store.constants.ResourcePath;
import store.domain.Cart;
import store.domain.readers.ProductReader;
import store.domain.readers.PromotionReader;
import store.domain.store.ConvenienceStoreroom;
import store.service.OrderService;
import store.view.Input;
import store.view.Output;

public class ConvenienceController {
    private OrderService orderService;

    public void run() {
        ConvenienceStoreroom storeroom = createConvenienceStoreroom();
        orderService = new OrderService(storeroom, new Cart());

        System.out.println(storeroom);
        buy();

    }
    

    public void buy() {
        while (true) {
            try {
                orderService.registerOrder(Input.inputOrder());
                return;
            } catch (IllegalArgumentException e) {
                Output.printError(e.getMessage());
            }
        }
    }

    public ConvenienceStoreroom createConvenienceStoreroom() {
        return new ConvenienceStoreroom(
                new ProductReader(), new PromotionReader(),
                ResourcePath.PRODUCT.get(), ResourcePath.PROMOTION.get());
    }
}
