package store.domain.store.item;

public class PromotionProduct extends Product {
    private final Promotion promotion;

    public PromotionProduct(String name, int price, int quantity, Promotion promotion) {
        super(name, price, quantity);
        this.promotion = promotion;
    }


    @Override
    public String toString() {
        String printout = "%s %s";
        return String.format(printout, super.toString(), promotion.getName());
    }
}