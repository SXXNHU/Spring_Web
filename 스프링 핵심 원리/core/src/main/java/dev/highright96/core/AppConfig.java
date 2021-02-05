package dev.highright96.core;

import dev.highright96.core.discount.DiscountPolicy;
import dev.highright96.core.discount.RateDiscountPolicy;
import dev.highright96.core.member.MemberRepository;
import dev.highright96.core.member.MemberServiceImpl;
import dev.highright96.core.member.MemoryMemberRepository;
import dev.highright96.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberRepository MemberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberServiceImpl memberService(){
        return new MemberServiceImpl(MemberRepository());
    }

    @Bean
    public OrderServiceImpl orderService(){
        return new OrderServiceImpl(MemberRepository(), discountPolicy());
    }
}
