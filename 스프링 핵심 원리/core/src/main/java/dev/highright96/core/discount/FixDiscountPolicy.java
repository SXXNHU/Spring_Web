package dev.highright96.core.discount;

import dev.highright96.core.member.Grade;
import dev.highright96.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; //1000원 할인

    @Override
    public int discount(Member member, int price) {
        if(Grade.VIP == member.getGrade()){
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}
