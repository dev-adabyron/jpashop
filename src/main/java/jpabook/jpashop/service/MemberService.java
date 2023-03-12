package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {


    @Autowired
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    회원 가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /*
    전체회원조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /*
    하나
     */
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    private void validateDuplicateMember(Member member) {
        if(!memberRepository.findByName(member.getName()).isEmpty()){
            throw new IllegalStateException("이미존재하는 회원입니다");
        }
    }
}
