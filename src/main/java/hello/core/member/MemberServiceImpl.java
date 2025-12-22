package hello.core.member;

/**
 *
 * 이 방식이 바로 생성자 주입이다. 여기에서는 MemoryMemberRepository 라는걸 명시하지 않고
 * AppConfig 파일 안에 설정해주기 떄문에 DIP 위반X. 즉, 영향이 없다.
 *  private final MemberRepository memberRepository;
 *
 *     public MemberServiceImpl(MemberRepository memberRepository)
 *     {
 *         this.memberRepository = memberRepository;
 *     }
 */

public class MemberServiceImpl implements MemberService
{
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository; //추상에만 의존하면 된다. 이제 구체 클래스를 몰라도 된다.

    public MemberServiceImpl(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member)
    {
        memberRepository.save(member);
    }

    @Override
    public Member findMemeber(Long memberID)
    {
        return memberRepository.findById(memberID);
    }
}
