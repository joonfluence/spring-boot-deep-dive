package spring.jpa.domain.order;

import jakarta.persistence.*;
import lombok.*;
import spring.jpa.domain.BaseTimeEntity;
import spring.jpa.domain.delivery.Delivery;
import spring.jpa.domain.delivery.DeliveryStatus;
import spring.jpa.domain.member.Member;
import spring.jpa.domain.orderItem.OrderItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "orders")
@Entity
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        // orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = Order.builder().member(member).delivery(delivery).status(OrderStatus.ORDER).orderItems(List.of(orderItems)).build();
        Arrays.stream(orderItems).forEach(order::addOrderItem);
        return order;
    }

    //==비즈니스 로직==//
    /**
     * 주문 취소
     */
    public void cancel(){
        if(delivery.getDeliveryStatus() == DeliveryStatus.DELIVERED){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가합니다");
        }
        this.status = OrderStatus.CANCEL;
        orderItems.forEach(OrderItem::cancle);
    }

    //==조회 로직==//
    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice(){
        return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }
}
