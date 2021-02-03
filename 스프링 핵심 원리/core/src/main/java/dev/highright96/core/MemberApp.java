package dev.highright96.core;

import dev.highright96.core.member.Grade;
import dev.highright96.core.member.Member;
import dev.highright96.core.member.MemberService;
import dev.highright96.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(memberA.getId());
        System.out.println("new member : " + memberA.getName());
        System.out.println("find member : " + findMember.getName());
    }
}
