package inflearn.core.domain.discount;

import inflearn.core.domain.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);
}
