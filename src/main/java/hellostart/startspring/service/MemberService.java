package hellostart.startspring.service;

import hellostart.startspring.domain.Member;
import hellostart.startspring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@를 이용하는 것 :  컴포넌트 스캔과 자동 의존관계 설정하는 법 (스프링빈을 등록하는 방법1)
// 컴포넌트 : @Service안에 들어가보면 @Component라 선언되어있다. -> 컨포넌트 스캔
// @Component가 있으면 스프링이 올라올때 스프링 객체 방식으로 생성해서 빈으로 자동 등록된다.
// 같은 패키지 내에 있어야만 컴포넌트 스캔의 대상이 된다.
@Service
public class MemberService {

    private final MemberRepository memberRepository ;

    // MemberService입장에서 직접 new하지 않고,
    // 외부에서 주입해서 만들어주도록 했으니 -> DI 라고 한다.
    @Autowired // Autowired는 연관관계를 의미한다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     **/
    public Long join(Member member){

        //같은 이름이 있는 중복 회원 X
        //주석a
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(member1 ->{
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        //같은 이름이 있는 중복 회원 받지 않게 하기
        validateDuplicateMember(member);// 중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //위의 주석 a와 같은 말
        //하나의 로직이 만들어지므로 메소드로 만드는 것이 좋다.
        memberRepository.findByName(member.getName())
            .ifPresent(member1 ->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
             });
    }

    /**전체 멤버 조회하기**/
   public List<Member> findMember(){
       return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memerId){
       return memberRepository.findById(memerId);
    }
}
