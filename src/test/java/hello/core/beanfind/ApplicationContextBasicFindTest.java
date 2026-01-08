package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
/**
 * 스프링 컨테이너에서 스프링 빈을 찾는 가장 기본적인 조회 방법
 * ac.getBean(빈이름, 타입)
 * ac.getBean(타입)
 *
 * 조회 대상 스프링 빈이 없으면 예외 발생
 * NoSuchBeanDefinitionException: No bean named 'xxxxx' available
 */
public class ApplicationContextBasicFindTest
{
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName()
    {
//        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입만으로 조회")
    void findBeanByType()
    {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회") //사실 좋은 방법은 아님, 역할과 구현을 구분해야하기 때문.
     void findBeanByName2()
    {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX()
    {
//        ac.getBean("xxxxxx", MemberService.class); // bean 이름에 xxxxxx가 없기 때문에 NoSuchBeanDefinitionException 이 발생한다.
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxxx", MemberService.class));
    }
}
