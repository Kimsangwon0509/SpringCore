package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicSacn() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = applicationContext.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
    /*
    AnnotationConfigApplicationContext 를 쓰는것은 기존과 동일
    다만 설정 정보로 'AutoAppConfig'클래스를 넘겨준다.
    실행해보면 기존과 같이 잘 동작하는 것을 알수 있다.

    10:25:10.323 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'autoAppConfig'
    10:25:10.338 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'rateDiscountPolicy'
    10:25:10.341 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'memberServiceImpl'
    10:25:10.391 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'memoryMemberRepository'

    실행해 보면 싱글톤 빈에 등록되는 것을 알수 있다.

    @ComponentScan은 @Component가 붙은 모든 클래스를 스프링 빈으로 등록한다.
    이때 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞
    */

}
