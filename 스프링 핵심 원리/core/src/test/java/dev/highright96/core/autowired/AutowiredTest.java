package dev.highright96.core.autowired;

import dev.highright96.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{

        private Member member;

//        public TestBean(@Nullable Member member) {
//            this.member = member;
//        }

        @Autowired(required = false)
        public void setNoBean1(Member member){
            System.out.println("Bean1 = " + member);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member){
            System.out.println("Bean2 = " + member);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member){
            System.out.println("Bean3 = " + member);
        }
    }
}
