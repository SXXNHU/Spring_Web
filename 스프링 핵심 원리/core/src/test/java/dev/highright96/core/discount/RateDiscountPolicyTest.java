package dev.highright96.core.discount;

import dev.highright96.core.member.Grade;
import dev.highright96.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    DiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 10% 할인 적용 성공 테스트")
    void vip() {
        //given
        Member member1 = new Member(1L, "memberVIP", Grade.VIP);
        Member member2 = new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discount1 = rateDiscountPolicy.discount(member1, 10000);
        int discount2 = rateDiscountPolicy.discount(member2, 20000);

        //then
        //Assertions => static import 하면 더 편하다.
        assertThat(discount1).isEqualTo(1000);
        assertThat(discount2).isEqualTo(2000);
    }

    @Test
    @DisplayName("BASIC 10% 할인 미적용 성공 테스트")
    void basic(){
        //given
        Member member1 = new Member(1L, "memberBASIC", Grade.BASIC);

        //when
        int discount1 = rateDiscountPolicy.discount(member1, 10000);

        //then
        assertThat(discount1).isEqualTo(0);
    }
}