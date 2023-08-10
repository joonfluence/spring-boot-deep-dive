package inflearn.core.config;

import inflearn.core.domain.discount.DiscountPolicy;
import inflearn.core.domain.discount.RateDiscountPolicy;
import inflearn.core.domain.member.MemberRepository;
import inflearn.core.domain.member.MemberService;
import inflearn.core.domain.member.MemberServiceImpl;
import inflearn.core.domain.member.MemoryMemberRepository;
import inflearn.core.domain.order.OrderService;
import inflearn.core.domain.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 싱글톤 컨테이너 보장
public class AppConfig {
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    // 메모리 상에 저장한다 (이 코드만 변경해주면 된다)
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 할인 정책은 정률 정책이다 (이 코드만 변경해주면 된다)
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
