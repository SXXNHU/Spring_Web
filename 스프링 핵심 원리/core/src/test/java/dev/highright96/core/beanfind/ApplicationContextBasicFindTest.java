package dev.highright96.core.beanfind;

import dev.highright96.core.AppConfig;
import dev.highright96.core.member.MemberService;
import dev.highright96.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService : " + memberService);
        System.out.println("memberService getClass() : " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    
    @Test
    @DisplayName("이름없이 타입으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);

        System.out.println("memberService : " + memberService);
        System.out.println("memberService getClass() : " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름없이 구체 타입으로 조회")
    // 스프링빈에는 구현 객체가 등록되기 때문에 가능
    // 그러나 역활과 구현 중에 역활에 의존해야하므로, 이 코드는 좋은 코드가 아니다. 유연성이 떨어진다.
    void findBeanByType2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);

        System.out.println("memberService : " + memberService);
        System.out.println("memberService getClass() : " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class));
    }
}
