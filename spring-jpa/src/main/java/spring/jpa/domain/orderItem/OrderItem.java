package spring.jpa.domain.orderItem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import spring.jpa.domain.order.Order;
import spring.jpa.domain.product.Product;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderItem_id", nullable = false)
    private Long id;
    private Integer orderPrice;
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    //==생성 메서드==//
    public static OrderItem createOrderItem(Product product, int orderPrice, int count){
        OrderItem orderItem = OrderItem.builder().product(product).orderPrice(orderPrice).count(count).build();
        product.removeStock(count);
        return orderItem;
    }

    //==비즈니스 로직==//
    public void cancle() {
        getProduct().addStock(count);
    }

    public int getTotalPrice(){
        return getOrderPrice() * getCount();
    }
}
