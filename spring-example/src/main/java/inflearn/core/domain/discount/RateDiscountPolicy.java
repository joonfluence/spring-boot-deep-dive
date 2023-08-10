package inflearn.core.domain.discount;

import inflearn.core.annotation.MainDiscountPolicy;
import inflearn.core.domain.member.Grade;
import inflearn.core.domain.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }

        return 0;
    }
}
