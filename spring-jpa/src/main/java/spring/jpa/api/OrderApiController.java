package spring.jpa.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.jpa.domain.address.Address;
import spring.jpa.domain.order.Order;
import spring.jpa.domain.order.OrderStatus;
import spring.jpa.domain.orderItem.OrderItem;
import spring.jpa.repository.OrderRepository;
import spring.jpa.repository.order.query.OrderFlatDto;
import spring.jpa.repository.order.query.OrderQueryDto;
import spring.jpa.repository.order.query.OrderQueryRepository;
import spring.jpa.service.OrderSearch;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); // LAZY 강제 초기화
            order.getDelivery().getAddress(); // LAZY 강제 초기화
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().forEach(o -> o.getProduct().getName()); // LAZY 강제 초기화
        }
        return all;
    }

    @GetMapping("/api/v1/orders/{id}")
    public Order orderV1(@PathVariable String id){
        Order order = orderRepository.findById(Long.valueOf(id));
        order.getMember().getName(); // LAZY 강제 초기화
        order.getDelivery().getAddress(); // LAZY 강제 초기화
        List<OrderItem> orderItems = order.getOrderItems();
        orderItems.stream().forEach(o -> o.getProduct().getName()); // LAZY 강제 초기화
        return order;
    }

    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        return orders.stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @GetMapping("/api/v3/orders")
    public List<OrderDto> orderV3(){
        List<Order> orders = orderRepository.findAllWithItem();
        return orders.stream().map(OrderDto::new).toList();
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> orderV3_page(@RequestParam(value = "offset", defaultValue = "0") int offset, @RequestParam(value = "limit", defaultValue = "100") int limit){
        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);
        return orders.stream().map(OrderDto::new).toList();
    }

    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4(){
        return orderQueryRepository.findOrderQueryDtos();
    }

    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> ordersV5() {
        return orderQueryRepository.findAllByDto_optimization();
    }

    @GetMapping("/api/v6/orders")
    public List<OrderFlatDto> ordersV6() {
        return orderQueryRepository.findAllByDto_flat();
    }

    @Data
    static class OrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime createdDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            this.orderId = order.getId();
            this.name = order.getMember().getName(); // LAZY
            this.createdDate = order.getCreatedDate();
            this.orderStatus = order.getStatus();
            this.address = order.getMember().getAddress(); // LAZY
            this.orderItems = order.getOrderItems().stream().map(OrderItemDto::new).toList(); // LAZY
        }
    }

    @Data
    static class OrderItemDto {
        private String itemName;
        private int orderPrice;
        private int count;

        public OrderItemDto(OrderItem orderItem) {
            this.itemName = orderItem.getProduct().getName(); // LAZY
            this.orderPrice = orderItem.getOrderPrice();
            this.count = orderItem.getCount();
        }
    }
}
