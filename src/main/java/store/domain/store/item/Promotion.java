package store.domain.store.item;

import java.time.LocalDateTime;

public class Promotion {
    private final String name;
    private final int buy;
    private final int get;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Promotion(String name, int buy, int get,
                     LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isValidDate(LocalDateTime date) {
        if (endDate.isAfter(date) && startDate.isBefore(date)) {
            return true;
        } else if (startDate.equals(date) || endDate.equals(date)) {
            return true;
        }
        return false;
    }

    public boolean isOverMinBuyQuantity(int quantity) {
        return quantity >= buy;

    }

    public int getGetCount() {
        return get;
    }

    public int calculatePromotionCount(int purchaseQuantity) {
        if (purchaseQuantity >= buy) {
            int bundle = purchaseQuantity / getPromotionBundle();
            return bundle * getPromotionBundle();
        }
        return 0;
    }

    private int getPromotionBundle() {
        return get + buy;
    }

    public String getName() {
        return name;
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }

}
