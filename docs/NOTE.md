### 기본 편의점 데이터

- ProductReader
- PromotionReader

- ConvenienceStoreroom : 재고 관리의 중심
    - Products
    - Promotions

- Products
    - List<Product>
- Promotions

- Product : 이름, 수량, 가격이 있는 일반 상품
    - PromotionProduct extend Product : 일반 상품에서 프로모션 필드 추가

---

### 주문

- OrderParser : String -> OrderForm - 정규식캡쳐사용
- OrderForm(r)

- Order
    - Product : 구매한 상품
    - quantity : 구매 수량
- Cart
    - List<Order> 일반 상품
    - List<Order> 프로모션 상품

---

### 서비스

- 상품 구매
- 프로모션 여부
- 영수증 생성