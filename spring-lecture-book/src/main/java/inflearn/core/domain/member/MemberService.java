package inflearn.core.domain.member;

public interface MemberService {
    void join (Member member);
    Member findMember(Long memberId);
}
