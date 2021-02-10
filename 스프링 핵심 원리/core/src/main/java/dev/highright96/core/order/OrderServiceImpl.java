package dev.highright96.core.order;

import dev.highright96.core.discount.DiscountPolicy;
import dev.highright96.core.member.Member;
import dev.highright96.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    // 생성자 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //테스트용
   public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    // SRP(단일 책임 원칙) 을 매우 잘지켜서 설계한 이유 => 주문을 생성하는 orderService 는 어떻게 member 를 찾는지, discountPolicy 가 무엇인지 모른다.
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 최종 생성된 주문을 반환
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
