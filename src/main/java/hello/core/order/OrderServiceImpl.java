package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long MemberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(MemberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        Order order = new Order(MemberId, itemName, itemPrice, discountPrice);
        return order;
    }
}
