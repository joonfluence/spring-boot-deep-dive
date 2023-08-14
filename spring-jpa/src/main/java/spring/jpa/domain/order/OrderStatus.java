package spring.jpa.domain.order;

public enum OrderStatus {
    PAID, // 구매
    CANCEL, // 취소
    REFUNDED, // 환불 완료
    RETURNED, // 교환 완료
    DELIVERED // 배송 완료
}
