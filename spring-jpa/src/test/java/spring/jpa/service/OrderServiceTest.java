package spring.jpa.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.jpa.domain.address.Address;
import spring.jpa.domain.member.Member;
import spring.jpa.domain.order.Order;
import spring.jpa.repository.OrderRepository;
import spring.jpa.domain.order.OrderStatus;
import spring.jpa.domain.product.Book;
import spring.jpa.domain.product.Product;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;


    @DisplayName("1. 상품 주문이 제대로 되야 한다.")
    @Test
    void test_1(){
        // given
        // 1. 주소/고객
        Member member = createMember();
        // 2. 책
        Product book = createBook("이코테", 10000, 10);
        // 3. 상품 갯수
        int orderCount = 3;

        // when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        Order order = orderRepository.findById(orderId);

        // then
        Assertions.assertEquals(order.getTotalPrice(), 10000 * orderCount);
    }

    @DisplayName("2. 상품 취소가 제대로 되야 한다.")
    @Test
    void test_2(){
        // given
        // 1. 주소/고객
        Member member = createMember();
        // 2. 책
        Product book = createBook("이코테", 10000, 10);
        // 3. 상품 갯수
        int orderCount = 3;

        // when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        orderService.cancelOrder(orderId);

        // then
        Order getOrder = orderRepository.findById(orderId);
        Assertions.assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
        Assertions.assertEquals(10, book.getStockQuantity());
    }


    private Member createMember() {
        Address address = Address.builder().address1("수노을 1리 81").address2("503동 503호").build();
        Member member = Member.builder().name("회원1").address(address).build();
        em.persist(member);
        return member;
    }

    private Product createBook(String name, int price, int quantity) {
        Product book = Book.builder().name(name).price(price).stockQuantity(quantity).build();
        em.persist(book);
        return book;
    }
}