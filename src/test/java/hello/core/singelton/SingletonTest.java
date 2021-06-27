package hello.core.singelton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig config = new AppConfig();
        //1.조회: 호출할 때 마다 객체를 생성하는지
        MemberService memberService1 = config.memberService();

        //2.조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = config.memberService();

        //참조 값이 다른것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        /*
        * memberService1 = hello.core.member.MemberServiceImpl@22ff4249
          memberService2 = hello.core.member.MemberServiceImpl@2d1ef81a
        * 서로 다른 객체가 생성된다. 결국 호출할 때마다 객체를 새로 생성한다.
        * */

        //항상 자동화 해 놓을것
        //memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }
}
