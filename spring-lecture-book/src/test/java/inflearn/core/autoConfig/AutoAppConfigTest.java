package inflearn.core.autoConfig;

import inflearn.core.config.AppConfig;
import inflearn.core.domain.member.MemberRepository;
import inflearn.core.domain.member.MemberService;
import inflearn.core.domain.member.MemberServiceImpl;
import inflearn.core.domain.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan(){
        // given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // when
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        // then
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = bean.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
    }
}
