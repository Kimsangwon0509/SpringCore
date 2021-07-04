package hello.core.singelton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = memberService.getMemberRepository();

        System.out.println("memberService -> memberRepository " + memberService);
        System.out.println("orderService -> memberRepository " + orderService);
        System.out.println("memberRepository = " + memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
    /*
    * @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서
    * 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어 질것이다
    *
    * 덕분에 싱글톤이 보장된다.
    *
    * if(스프링 컨테이너가 등록되있으면?){
    *   return 스프링 컨테이너를 찾아서 반환}
    * else {
    *   기존 로직을 호출해서 Bean울 생성하고 스프링 컨테이너에 등록
    *   return 반환}
    * 같은 위의 코드가 동적으로 만들어 질것으로 예상
    * */
    
    /*
    * 정리
    * @Bean 만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.
    * 크게 고민할것 없이 , 스프링 설정 정보는 항상 @Configuration을 사용하자
    * */
}
