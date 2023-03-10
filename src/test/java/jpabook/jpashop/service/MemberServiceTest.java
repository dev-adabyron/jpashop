package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Rollback(value = false)
    @Test
    public void 회원가입() {
        //given
        Member member=new Member();
        member.setName("Lee");

        //when
        Long saveId = memberService.join(member);


        //then
        assertEquals(member,memberRepository.findOne(saveId));

    }

    @Test
    public void 중복_회원_예외(){

    }
}