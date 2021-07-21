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

@Configuration
public class AppConfig {

    //어딘가에서 memberService를 가져다가 쓸때 appconfig에서 설정한 메모리멤버 리포지토리를 가져다가 쓰게된다.
    //**생성자 주입!!
    @Bean
    public MemberService memberService() {

        return  new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
        //return new FixDiscountPolicy();
    }

/*
    AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다
    AppConfig는 생성한 객체 인스턴스의 참조(래퍼런스)를 생성자를 통해서 주입(연결) 해준다.
*/

/*
    객체의 생성과 연결은 AppConfig가 담당한다
    DIP 완성: MemberServiceImpl은 MemberRepository인 추사에만 의존하면 된다. 이제 구체 클래스를 몰라도 된다.
    관심사의 분리: 객체를 생성하고 연결하는 역활과 실행하는 역활이 명확히 분리되었다.
*/

/*
*   appConfig 객체는 memoryMemberRepository 객체를 생성하고 그 참조값을 memberServiceImpl을 생성하면서 생성자로 전달한다.
*   클라이언트인 memberServiceImpl 입장에서보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI 우리말로 의존관계 주입 또는 의존성 주입이라고 한다.
* */

    /*
     * new MemoryRepository() 부분의 중복이 제거되었다. 이제 MemoryMemberRopository를 다른 구현체로 변경할 때 한 부분만 변경하면 된다
     * AppConfig 를 보면 역활과 구현 클래스가 한눈에 들어온다. 애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악할 수 있다.
     * */

    /*
    * AppConfig에서 할인 정책 역활을 담당하는 구현을 FixDiscountPolicy에서 RateDiscountPolicy 객체로 변경했다
    * 이제 할인 정책을 변경해도, 애플리케이션의 구성 역활을 담당하는 AppConfig만 변경하면 된다. 클라이언트 코드인 OrderServiceIml를 포함해서 사용 영역의 어떤 코드도 변경할 필요가 없다
    * 구성 영역(AppConfig)은 당연히 변경된다. 구성 역활을 담당하는 AppConfig를 애플리케이션이라는 공연의 기획자로 생각. 공연 기획자는 공연 참여자인 구현 객체들을 모두 알아야 한다.
    * */
}
