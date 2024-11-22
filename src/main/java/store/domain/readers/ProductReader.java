package store.domain.readers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import store.domain.store.item.Product;
import store.domain.store.item.Promotion;
import store.domain.store.item.PromotionProduct;
import store.domain.store.items.Promotions;

public class ProductReader {

    private boolean havePromotion = false;

    public List<Product> read(String path, Promotions promotions) {
        List<Product> result = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(",");

                String name = splitLine[0];
                int price = Integer.parseInt(splitLine[1]);
                int quantity = Integer.parseInt(splitLine[2]);
                String promotionName = splitLine[3];

                if (havePromotion) {
                    Product promotionProduct = result.getLast();
                    if (!promotionProduct.isSameName(name)) {
                        result.add(promotionProduct.makeSoldOutProduct());
                    }
                }

                //note 일반 상품
                if (promotionName.equals("null")) {
                    havePromotion = false;
                    Product generalProduct = new Product(name, price, quantity);
                    result.add(generalProduct);
                    continue;
                }

                //note 프로모션 상품
                havePromotion = true;
                Promotion promotion = promotions.findByName(promotionName);
                Product promotionProduct = new PromotionProduct(name, price, quantity, promotion);
                result.add(promotionProduct);
            }
        } catch (IOException e) {
            throw new IllegalStateException("재품 등록 오류");
        }

        return result;
    }
}
