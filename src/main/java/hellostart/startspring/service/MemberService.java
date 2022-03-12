package hellostart.startspring.service;

import hellostart.startspring.domain.Member;
import hellostart.startspring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository ;

    // MemberService입장에서 직접 new하지 않고,
    // 외부에서 주입해서 만들어주도록 했으니 -> DI 라고 한다.

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
