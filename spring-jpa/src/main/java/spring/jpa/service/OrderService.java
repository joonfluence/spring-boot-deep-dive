package spring.jpa.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.jpa.domain.delivery.Delivery;
import spring.jpa.domain.delivery.DeliveryStatus;
import spring.jpa.domain.member.Member;
import spring.jpa.domain.member.MemberRepository;
import spring.jpa.domain.order.Order;
import spring.jpa.domain.order.OrderRepository;
import spring.jpa.domain.orderItem.OrderItem;
import spring.jpa.domain.product.Product;
import spring.jpa.domain.product.ProductRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    /**
     * 주문 생성
     * @param memberId
     * @param productId
     * @param count
     * @return
     */
    @Transactional
    public Long order(Long memberId, Long productId, int count){
        // 엔티티 조회
        Member member = memberRepository.findById(memberId);
        Product product = productRepository.findById(productId);

        // 배송정보 생성
        Delivery delivery = Delivery.builder().address(member.getAddress()).deliveryStatus(DeliveryStatus.DELIVERING).build();

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(product, product.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.save(order);
        return order.getId();
    }

    /**
     * 주문 취소
     * @param orderId
     */
    @Transactional
    public void cancelOrder(Long orderId){
        // 주문 엔티티 조회
        Order order = orderRepository.findById(orderId);
        // 주문 취소
        order.cancel();
    }

    /**
     * 주문 취소
     */
    //    public List<Order> findOrders(OrderSearch orderSearch){
    //        return orderRepository.findAll(orderSearch);
    //    }
}
