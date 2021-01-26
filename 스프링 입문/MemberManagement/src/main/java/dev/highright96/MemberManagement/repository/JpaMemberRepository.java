package dev.highright96.MemberManagement.repository;

import dev.highright96.MemberManagement.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


/*
jpa 는 mybatis 보다 세게쩍으로 더 많이 쓰인다...
jpa 는 spring 만큼이나 매우 깊고 넓다....
jpa 는 표준 인터페이스, 구현체는 하이버네이트(구현 업체) 만 거의 쓴다... 업체마다 성능이 좋고 쓰기 편하고 차이가 있음.
jpa 는 \orm(오브젝트 릴레이셔널 맵핑) 이라는 기술
 */

public class JpaMemberRepository implements MemberRepository {

    /*
    jpa 는 엔티티매니저로 모든 것이 동작한다.
    jpa 라이브러리를 받으면 스프링부트가 엔티티매니저를 생성해준다(application.properties 로 디비 연결하고 등등,.,,)
    jpa 를 쓸려면 엔티티매니저를 주입받아야한다.
     */
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        //insert 쿼리
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // pk 로 select 쿼리를 보낼때
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // pk 기반이 아닌 나머지들은 jpql 을 작성해야한다.
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> members = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return members.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // pk 가 아닌 컬럼으로 select 쿼리를 보낼때
        // jpql 쿼리언어, 테이블을 대상으로 쿼리를 보내는것이 아니라 객체(엔티티)를 대상으로 쿼리를 보낸다.
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
