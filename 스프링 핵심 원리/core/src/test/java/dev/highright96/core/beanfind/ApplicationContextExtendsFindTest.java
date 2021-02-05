package dev.highright96.core.beanfind;

import dev.highright96.core.discount.DiscountPolicy;
import dev.highright96.core.discount.FixDiscountPolicy;
import dev.highright96.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 등록된 자식이 둘 이상 있으면, 중복오류가 발생한다.")
    void findByParentTypeDuplicate(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                ()-> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 등록된 자식이 둘 이상 있으면, 빈 이름을 지정한다.")
    void findBeanBySubName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("RateDiscountPolicy", DiscountPolicy.class);
        DiscountPolicy fixDiscountPolicy = ac.getBean("FixDiscountPolicy", DiscountPolicy.class);

        assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);
        assertThat(fixDiscountPolicy).isInstanceOf(DiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회.")
    void findBeanBySubType(){
        DiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        DiscountPolicy fixDiscountPolicy = ac.getBean(FixDiscountPolicy.class);

        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
        assertThat(fixDiscountPolicy).isInstanceOf(FixDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Object 타입으로 모두 조회하기")
    void findAllBeanByObjectType(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String s : beansOfType.keySet()) {
            System.out.println("key = " + s + ", value = " + beansOfType.get(s));
        }
    }

    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy RateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy FixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }

}
