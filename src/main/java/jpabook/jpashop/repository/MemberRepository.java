package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MemberRepository {
    @Autowired
    private EntityManager em;

    //저장

    public void save(Member member){
        em.persist(member);
    }

    //전체조회

    public List<Member> findAll(){
        return em.createQuery("select m from Member m",Member.class).getResultList();
    }

    //ID로 조회

    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    //이름으로 조회

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();

    }


}
