package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ApplicationContext를 스프링 컨테이너라고 한다.
 * 기존에는 개발자가 AppConfig 를 사용해서 직접 객체를 생성하고 DI를 했지만, 이제부터는 스프링 컨테이너를 통해서 사용한다.
 * 스프링 컨테이너는 '@Configuration'이 붙은 'AppConfig'를 설정(구성) 정보로 사용한다.
 * 여기서 '@Bean'이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다.
 * 이렇게 스프링 컨테이너에 등록된 객체를 스프링 빈이라 한다.
 */
public class MemberApp
{
    public static void main(String[] args)
    {
//        AppConfig appConfig = new AppConfig();// version2
//        MemberService service = appConfig.memberService(); // version2
//        MemberService service = new MemberServiceImpl(); // version1

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMemeber(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
