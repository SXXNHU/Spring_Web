package dev.highright96.core.discount;

import dev.highright96.core.member.Grade;
import dev.highright96.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{
    
    private final int discountFixAmount = 1000; //1000원 할인
    
    @Override
    public int discount(Member member, int price) {
        if(Grade.VIP == member.getGrade()){
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}
