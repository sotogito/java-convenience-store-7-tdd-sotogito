package store.domain.item;

public class Product {
    private final String name;
    private final int price;
    private int quantity;

    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }

    public Product makeSoldOutProduct() {
        return new Product(this.name, this.price, 0);
    }

    @Override
    public String toString() {
        String printout = "- %s %,d원 %,d개";
        String soldOutPrintout = "- %s %,d원 재고 없음";

        if (quantity <= 0) {
            return String.format(soldOutPrintout, name, price);
        }
        return String.format(printout, name, price, quantity);
    }

}
