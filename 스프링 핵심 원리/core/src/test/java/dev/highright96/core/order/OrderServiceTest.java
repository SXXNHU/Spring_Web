package dev.highright96.core.order;

import dev.highright96.core.AppConfig;
import dev.highright96.core.member.Grade;
import dev.highright96.core.member.Member;
import dev.highright96.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    MemberService memberService ;
    OrderService orderService;

    @BeforeEach
    void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        //when
        Order order = orderService.createOrder(member.getId(), "itemA", 20000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(2000);
    }
}