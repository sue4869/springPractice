package hellostart.startspring;

import hellostart.startspring.repository.MemberRepository;
import hellostart.startspring.repository.MemoryMemberRepository;
import hellostart.startspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
