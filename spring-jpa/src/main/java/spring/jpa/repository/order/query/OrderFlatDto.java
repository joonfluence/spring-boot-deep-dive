package spring.jpa.repository.order.query;

import lombok.Data;
import spring.jpa.domain.address.Address;
import spring.jpa.domain.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderFlatDto {
    private Long orderId;
    private String name;
    private LocalDateTime createdDate; //주문시간
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemQueryDto> orderItems;
    private String itemName; //상품 명
    private int orderPrice; //주문 가격
    private int count; // 주문 수량

    public OrderFlatDto(Long orderId, String name, LocalDateTime createdDate, OrderStatus orderStatus, Address address, String itemName, int orderPrice, int count) {
        this.orderId = orderId;
        this.name = name;
        this.createdDate = createdDate;
        this.orderStatus = orderStatus;
        this.address = address;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
