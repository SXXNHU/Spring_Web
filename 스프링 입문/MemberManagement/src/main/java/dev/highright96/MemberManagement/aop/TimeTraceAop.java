package dev.highright96.MemberManagement.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component //스프링 빈으로 등록하기 위해 컴포넌트스캔 사용, SpringConfig 파일에 직접 빈으로 등록을 권장
public class TimeTraceAop {

    @Around("execution(* dev.highright96.MemberManagement.service..*(..))")
    public Object execute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        //조인포인트 -> 적용되는 메소드
        System.out.println("START : " + proceedingJoinPoint.toString());
        try{
            // 적용되는 메소드의 핵심 로직
            return proceedingJoinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + proceedingJoinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
