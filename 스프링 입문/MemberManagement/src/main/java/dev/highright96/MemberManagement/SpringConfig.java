package dev.highright96.MemberManagement;

import dev.highright96.MemberManagement.repository.MemberRepository;
import dev.highright96.MemberManagement.repository.MemoryMemberRepository;
import dev.highright96.MemberManagement.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
