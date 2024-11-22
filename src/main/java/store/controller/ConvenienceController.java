package store.controller;

import store.constants.ResourcePath;
import store.domain.readers.ProductReader;
import store.domain.readers.PromotionReader;
import store.domain.store.ConvenienceStoreroom;

public class ConvenienceController {

    public void run() {
        ConvenienceStoreroom convenienceStoreroom = createConvenienceStoreroom();
        System.out.println(convenienceStoreroom);

    }

    public ConvenienceStoreroom createConvenienceStoreroom() {
        return new ConvenienceStoreroom(
                new ProductReader(), new PromotionReader(),
                ResourcePath.PRODUCT.get(), ResourcePath.PROMOTION.get());
    }
}
