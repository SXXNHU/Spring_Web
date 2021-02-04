package dev.highright96.core.member;

// 구현 객체가 하나인 경우 관례상 인터페이스 뒤에 Impl 을 붙인다.
public class MemberServiceImpl implements MemberService{
    
    // 추상화, 구현 객체 둘다 의존 => DIP 위반 => 추상화만 의존 => DIP 를 지킴
    private MemberRepository memberRepository;

    // 생성자 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
