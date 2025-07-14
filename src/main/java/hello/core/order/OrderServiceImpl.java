package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.stereotype.Component;


@Component

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long MemberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(MemberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        Order order = new Order(MemberId, itemName, itemPrice, discountPrice);
        return order;
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
