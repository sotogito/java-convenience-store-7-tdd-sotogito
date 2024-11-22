package store.constants;

public enum ResourcePath {
    PRODUCT("src/main/resources/products.md"),
    PROMOTION("src/main/resources/promotions.md");

    private final String path;

    ResourcePath(String path) {
        this.path = path;
    }

    public String get() {
        return path;
    }
}
