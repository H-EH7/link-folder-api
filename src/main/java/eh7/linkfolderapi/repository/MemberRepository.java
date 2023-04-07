package eh7.linkfolderapi.repository;

import eh7.linkfolderapi.model.Member;

public interface MemberRepository {
    void save(Member member);

    void delete(Long memberId);

    Member findById(Long memberId);

    // 테스트용
    void clearStore();
}
