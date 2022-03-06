package hellostart.startspring.service;

import hellostart.startspring.domain.Member;
import hellostart.startspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //테스트는 한글로 이름 적어도 된다.

    //매번 새로운 memberRepository 인스턴스를 만들게 된다 ==> 실제와 테스트는 다른 저장소이용
//    MemberService memberService = new MemberService(memberRepository);
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //같은 memberRepository를 사용하도록 만들어줌
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    //테스트 할때마다 저장소 clear시키기
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given : 이것이 주어지면
        Member member = new Member();
        member.setName("spring");

        // when : 이것을 실행했을때
        Long saveId = memberService.join(member);

        //then : 결과가 이게 나와야함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //try-catch대신 사용
        //() -> memberService.join(member2) 로직이 실행될때
        // IllegalStateException.class 의 예외 실행
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //then
    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}