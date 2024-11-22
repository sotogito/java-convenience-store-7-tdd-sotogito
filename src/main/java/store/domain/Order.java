package store.domain;

import store.constants.ProductType;
import store.domain.store.item.Product;
import store.domain.store.item.PromotionProduct;

public class Order {
    private final Product product;
    private final int quantity;

    public Order(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public boolean isCanAddablePromotionProduct() {
        if (product instanceof PromotionProduct promotionProduct) {
            int addableQuantity = getAddablePromotionCount();
            int totalQuantityAfterPromotion = quantity + addableQuantity;
            if (addableQuantity != 0 ||
                    promotionProduct.isSufficientQuantity(totalQuantityAfterPromotion)) {
                return true;
            }
        }
        return false;
    }

    //note 현재 {상품명}은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
    public int getAddablePromotionCount() {
        if (product instanceof PromotionProduct promotionProduct) {
            int withoutPromotionCount = getWithoutPromotionCount();
            if (promotionProduct.isValidMinBuyQuantity(withoutPromotionCount)) {
                return promotionProduct.getAddablePromotionCount();
            }
        }
        return 0;
    }

    public int getWithoutPromotionCount() {
        return quantity - ((PromotionProduct) product).getTotalPromotionCount(quantity);
    }


    public ProductType getProductType() {
        return product.getType();
    }

}
