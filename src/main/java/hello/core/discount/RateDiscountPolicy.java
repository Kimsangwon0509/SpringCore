package hello.core.discount;

public class RateDiscountPolicy implements DiscountPolicy{

    @Override
    public int discount(Member member, int price) {
        return 0;
    }
}
