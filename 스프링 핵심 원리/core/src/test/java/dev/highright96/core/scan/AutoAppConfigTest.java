package dev.highright96.core.scan;

import dev.highright96.core.AutoAppConfig;
import dev.highright96.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class AutoAppConfigTest {

    @Test
    public void basicScan() throws Exception{
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        //when
        MemberService memberService = ac.getBean(MemberService.class);

        //then
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
