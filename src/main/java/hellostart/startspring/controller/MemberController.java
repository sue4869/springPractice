package hellostart.startspring.controller;

import hellostart.startspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class MemberController {

    //DI 주입 방법 1: 생성자 주입.생성자에 의에 memberService가 memberController에 주입된다.
    // 의존관계가 실행중에 동적으로 변하는 경우(런타임에 중간에 바뀌는것)가 없기 때문에 권장
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    //DI 주입 방법 2:필드주입 -> 단점: 중간에 바꿔치기 할 방법이 없으므로 잘 안쓰는 방법
/*
    @Autowired
    private MemberService memberService;
*/

    //DI 주입 방법 3:setter주입 -> 단점: set생성자 public으로 열려있어야한다.아무나 접근가능
/*
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
 */
}
