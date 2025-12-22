package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService
{
    // 우리는 역할과 구현을 충실하게 분리했다 -> O
    // DiscountPolicy ( 역할 ) / FixDiscountPolicy, RateDiscountPolicy ( 구현 )
    // 다형성도 활요하고, 인터페이스와 구현 객체를 분리했다 -> O ->

    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //DIP 위반이다.
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //DIP 위반이다.
    private DiscountPolicy discountPolicy; //DIP 위반 X.


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice)
    {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
