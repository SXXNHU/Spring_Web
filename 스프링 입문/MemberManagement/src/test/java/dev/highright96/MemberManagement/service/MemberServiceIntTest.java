package dev.highright96.MemberManagement.service;

import dev.highright96.MemberManagement.domain.Member;
import dev.highright96.MemberManagement.repository.MemberRepository;
import dev.highright96.MemberManagement.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        memberService.join(member);

        //then
        assertEquals(member.getName(), memberService.findOne(member.getId()).get().getName());
    }

    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("홍길동");
        Member member2 = new Member();
        member2.setName("홍길동");

        //when
        memberService.join(member1);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");
    }
}
