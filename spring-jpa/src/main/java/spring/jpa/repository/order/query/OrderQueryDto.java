package spring.jpa.repository.order.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import spring.jpa.domain.address.Address;
import spring.jpa.domain.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(of = "orderId")
public class OrderQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime createdDate; //주문시간
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemQueryDto> orderItems;
    public OrderQueryDto(Long orderId, String name, LocalDateTime orderDate,
                         OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.createdDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}