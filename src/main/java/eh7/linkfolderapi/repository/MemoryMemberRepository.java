package eh7.linkfolderapi.repository;

import eh7.linkfolderapi.model.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public void save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
    }

    @Override
    public void delete(Long memberId) {
        store.remove(memberId);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

    // 테스트용
    @Override
    public void clearStore() {
        sequence = 0L;
        store.clear();
    }
}
