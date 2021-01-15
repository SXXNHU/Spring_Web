package dev.highright96.MemberManagement;

import dev.highright96.MemberManagement.repository.JdbcMemberRepository;
import dev.highright96.MemberManagement.repository.MemberRepository;
import dev.highright96.MemberManagement.repository.MemoryMemberRepository;
import dev.highright96.MemberManagement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
