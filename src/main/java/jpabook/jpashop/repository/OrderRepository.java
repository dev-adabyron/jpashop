package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class OrderRepository {
    @Autowired
    EntityManager em;



    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class,id);
    }

    //public List<Order> findAll(OrderSearch orderSearch){ ... }

}
