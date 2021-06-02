package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //현재의 코드가 MemberRepository라는 추상화에도 의존하고 emoryMemberRepository라는 구체화에도 의존하므로 안좋은 코드가 된다.
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //앱콘피그에서 구현하기 위해서?

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } // 생성자를 통해서 memberRepsitory에 무엇이 들어갈지를 정한다.


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /*
    * 설계 변경으로 MemberServiceImpl은 MemoryMemberRepository를 의존하지 않는다
    * 단지 MemberRepository 인터페이스만 의존한다
    * MemberServiceImpl 입장에서 생성자를 통해 어떤 구현 객체가 들오올지(의존객체)는 알 수 없다.
    * MemberServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(config)에서 결정된다
    * MemberServiceImpl은 이제부터 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면된다.
    * */
}
