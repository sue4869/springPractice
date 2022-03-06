package hellostart.startspring.repository;

import hellostart.startspring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);//시스템으로 id 정해줌
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //가져온 값이 null일 수 있으니 Optional로 감싼다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //파라미터에서 넘어온 name과 같은지 확인.같은 경우에만 필터링
                .findAny(); //그중에서 찾으면 반환. 없으면 null로
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store에 있는 멤버 반환
    }

    public void clearStore(){
        store.clear();
    }
}
