package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    //어딘가에서 memberService를 가져다가 쓸때 appconfig에서 설정한 메모리멤버 리포지토리를 가져다가 쓰게된다.
    //**생성자 주입!!
    public MemberService memberService() {
        return  new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
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
}
