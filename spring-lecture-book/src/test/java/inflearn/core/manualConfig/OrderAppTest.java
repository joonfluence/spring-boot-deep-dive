package inflearn.core.manualConfig;

import inflearn.core.config.AppConfig;
import inflearn.core.domain.member.Grade;
import inflearn.core.domain.member.Member;
import inflearn.core.domain.member.MemberService;
import inflearn.core.domain.order.Order;
import inflearn.core.domain.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderAppTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getItemPrice()).isEqualTo(10000);
    }
}