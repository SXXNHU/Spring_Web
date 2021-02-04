package dev.highright96.core;

import dev.highright96.core.discount.DiscountPolicy;
import dev.highright96.core.discount.FixDiscountPolicy;
import dev.highright96.core.discount.RateDiscountPolicy;
import dev.highright96.core.member.MemberRepository;
import dev.highright96.core.member.MemberServiceImpl;
import dev.highright96.core.member.MemoryMemberRepository;
import dev.highright96.core.order.OrderServiceImpl;

public class AppConfig {

    private DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    private MemberRepository MemberRepository() {
        return new MemoryMemberRepository();
    }

    public MemberServiceImpl memberService(){
        return new MemberServiceImpl(MemberRepository());
    }

    public OrderServiceImpl orderService(){
        return new OrderServiceImpl(MemberRepository(), discountPolicy());
    }
}
