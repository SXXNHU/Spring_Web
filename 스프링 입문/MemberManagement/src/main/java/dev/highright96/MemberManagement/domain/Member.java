package dev.highright96.MemberManagement.domain;

import javax.persistence.*;

//jpa 가 관리하는 엔티티라고 표현
@Entity
public class Member {

    @Id //PK 임을 표시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "xxx") 컬럼명 과 필드 이름과 같으면 안해줘도됨
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
