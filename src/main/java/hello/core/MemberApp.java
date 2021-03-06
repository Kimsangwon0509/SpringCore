package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
     //  AppConfig appConfig = new AppConfig();
       // MemberService memberService = appConfig.memberService();
        //MemberService memberService = new MemberServiceImpl();

        //AppConfig의 환경 정보를 가지고 스프링 컨테이너에 등록해서 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);

        Member member = new Member(1L, "memberA", Grade.BASIC); // Long 타입이서 1뒤에 L을 붙임
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
        System.out.println("123");
    }
}
