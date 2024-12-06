package store.domain;

import store.constants.ProductType;
import store.domain.store.ConvenienceStoreroom;
import store.domain.store.item.Product;
import store.domain.store.item.PromotionProduct;

public class Order {
    private final Product product;
    private int quantity;

    public Order(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void addPurchaseQuantity(int addQuantity) {
        this.quantity += addQuantity;
    }

    public void deletePurchaseQuantity(int deleteQuantity) {
        this.quantity -= deleteQuantity;
    }

    public boolean isCanNotPromotionProduct() {
        if (product instanceof PromotionProduct promotionProduct) {
            int totalQuantityAfterPromotion = quantity + getAddablePromotionCount();
            if (!promotionProduct.isSufficientQuantity(quantity) ||
                    !promotionProduct.isSufficientQuantity(totalQuantityAfterPromotion)) {
                return true;
            }
        }
        return false;
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

    //note note 현재 {상품명} {수량}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
    public int getWithoutPromotionCount() {
        if (product instanceof PromotionProduct promotionProduct) {
            int validPromotionalOrderQuantity = promotionProduct.getValidPromotionQuantity(quantity);
            return quantity -
                    ((PromotionProduct) product).getTotalPromotionCount(validPromotionalOrderQuantity);
        }
        return 0;
    }

    public String getName() {
        return product.getName();
    }

    public ProductType getProductType() {
        return product.getType();
    }

    public Order makePromotionToGeneralProductByQuantity(int quantity, ConvenienceStoreroom storeroom) {
        return new Order(storeroom.findGeneralProductByName(product.getName()), quantity);
    }


    @Override
    public String toString() {
        return product.getName() + ":" + quantity;
    }

}
