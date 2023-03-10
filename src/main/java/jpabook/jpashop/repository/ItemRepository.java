package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository

public class ItemRepository {

    private EntityManager em;
    @Autowired
    public ItemRepository (EntityManager em){
        this.em=em;
    }

    public void saveItem(Item item){
        if(item.getId() == null){
            em.persist(item);
        }
        else {
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class,id);
    }
    public List<Item> findAll(){
       return em.createQuery("select I from Itme I",Item.class).getResultList();
    }
}
