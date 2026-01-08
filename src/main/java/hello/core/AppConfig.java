package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * AppConfig는 애플리케이션의 실제 동작에 필요한 "구현 객체를 생성"한다.
 * AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 "생성자를 통해서 주입(연결)" 해준다.
 */

@Configuration
public class AppConfig
{
    @Bean //@Bean 어노테이션은 항상 public 이어야한다.
    public MemberRepository memberRepository()
    {
        return new MemoryMemberRepository();
    }

    @Bean(name = "discountPolicy2") //이렇게 하면 스프링 컨테이너에 Bean repository에 discountPolicy -> discountPolicy2 라는 이름으로 저장된다.
    public DiscountPolicy discountPolicy()
    {
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService()
    {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService()
    {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
