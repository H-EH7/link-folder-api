package eh7.linkfolderapi.service;

import eh7.linkfolderapi.model.Member;

public interface MemberService {
    void join(Member member);

    void leave(Long memberId);

    Member findMember(Long memberId);
}
