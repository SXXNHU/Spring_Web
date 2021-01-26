package dev.highright96.MemberManagement.repository;

import dev.highright96.MemberManagement.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
인터페이스끼리는 extends 키워드로 상속을 받아야한다.
인터페이스는 다중 상속이 가능하다.
JpaRepository 를 상속받으면 SpringDataJpaMemberRepository 의 구현체를 자신의 기술로 만든 후 스프링 빈에 자동으로 등록한다. 따로 우리가 SpringConfig 에서 등록할 필요가 없다.
다만 MemberRepository 를 의존하는 에들에게 di는 해줘야한다.

JpaRepository (상속을 쭉 들어가보면 simple... 클래슥 나옴)에서 pk 로 할 수 있는 조회, 수정, 삭제, 등록(공통 로직)의 구현제들을 제공한다.
그러나 findByName 과 같이 비지니스 로직에 의해 바뀔 수 있는 추상 메소드들은 Jpa 의 메소드 이름 규칙에 따라 작성해주면 알아서 구현체를 제공한다.
findByName -> 이름으로 멤버 찾기
findByNameAndId -> name 과 id 로 멤버 찾기
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
