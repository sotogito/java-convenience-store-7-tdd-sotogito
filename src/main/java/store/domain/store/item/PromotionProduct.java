package store.domain.store.item;

import java.time.LocalDateTime;
import store.constants.ProductType;

public class PromotionProduct extends Product {
    private final Promotion promotion;

    public PromotionProduct(String name, int price, int quantity, Promotion promotion) {
        super(name, price, quantity);
        this.promotion = promotion;
    }

    public boolean isValidDate(LocalDateTime date) {
        return promotion.isValidDate(date);
    }

    public ProductType getType() {
        return ProductType.PROMOTION;
    }

    @Override
    public String toString() {
        String printout = "%s %s";
        return String.format(printout, super.toString(), promotion.getName());
    }
}
