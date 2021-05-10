package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //현재의 코드가 MemberRepository라는 추상화에도 의존하고 emoryMemberRepository라는 구체화에도 의존하므로 안좋은 코드가 된다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
