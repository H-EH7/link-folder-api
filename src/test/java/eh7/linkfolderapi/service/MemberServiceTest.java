package eh7.linkfolderapi.service;

import eh7.linkfolderapi.AutoAppConfig;
import eh7.linkfolderapi.model.Member;
import eh7.linkfolderapi.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    MemberService memberService = ac.getBean(MemberService.class);
    MemberRepository memberRepository = ac.getBean(MemberRepository.class);

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("회원가입")
    void join() {
        //given
        Member member = new Member("memberA", "email");

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    @DisplayName("회원탈퇴")
    void leave() {
        //given
        Member member1 = new Member("memberA", "email");
        Member member2 = new Member("memberB", "email");
        Member member3 = new Member("memberC", "email");

        memberService.join(member1);
        memberService.join(member2);
        memberService.join(member3);

        //when
        memberService.leave(2L);
        Member member = memberService.findMember(2L);

        //then
        assertThat(member).isEqualTo(null);
    }

    @Test
    @DisplayName("회원조회")
    void findMember() {
        //given
        Member member1 = new Member("memberA", "email");
        Member member2 = new Member("memberB", "email");
        Member member3 = new Member("memberC", "email");

        memberService.join(member1);
        memberService.join(member2);
        memberService.join(member3);

        //when
        Member findMember = memberService.findMember(2L);

        //then
        assertThat(findMember).isEqualTo(member2);
    }
}