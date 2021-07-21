package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private DiscountPolicy discountPolicy; // 인터페이스에 의존 구체화에 의존하지 않음 하지만 NULLPOINT 익셥센이 뜬다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    //@Autowired 생성자가 하나면 생략 가능!
    /*public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/  // 이 final 이 붙은 것을 가지고 RequiredArgsConstructor가 annotation으로 생성자를 만들어 준다 final이 붙어 있어야 된다.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


    /*
    * 설계 변경으로 OrderServiceImpl은 FixDiscountPolicy를 의존하지 않는다
    * 단지 DiscountPolicy 인터페이스만 의존한다.
    * OrderServiceImpl입장에서 생성자를 통해 어떤 구현 객체가 들오올지(주입될지)는 알수 없다.
    * OrderServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정한다
    * OrderServiceImpl은 이제부터 실행에만 집중한다
    *
    * 결과적으로 OrderServiceImpl에는 MemoryMemberRepository, FixDiscountPolicy 객체의 의존관계가 주입된다.
    * */
}
