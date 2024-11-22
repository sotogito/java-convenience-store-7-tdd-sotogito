package store.domain.store.items;

import java.util.List;
import store.domain.store.item.Promotion;

public class Promotions {
    private final List<Promotion> promotions;

    public Promotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Promotion findByName(String name) {
        for (Promotion promotion : promotions) {
            if (promotion.isSameName(name)) {
                return promotion;
            }
        }
        throw new IllegalStateException("존재하지 않는 프로모션");
    }
}
