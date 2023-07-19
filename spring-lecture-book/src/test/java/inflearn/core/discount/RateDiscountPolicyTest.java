package inflearn.core.discount;

import inflearn.core.domain.discount.RateDiscountPolicy;
import inflearn.core.domain.member.Grade;
import inflearn.core.domain.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 되어야 한다")
    void vip_o() {
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        // when
        int discount = discountPolicy.discount(member, 10000);
        // then
        Assertions.assertEquals(discount, 1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되면 안된다")
    void vip_x() {
        // given
        Member member = new Member(2L, "member", Grade.BASIC);
        // when
        int discount = discountPolicy.discount(member, 10000);
        // then
        Assertions.assertEquals(discount, 0);
    }
}