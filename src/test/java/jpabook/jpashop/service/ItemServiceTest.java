package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {
    //아이템등록
    @Autowired ItemService itemService;
    @Autowired ItemRepository itemRepository;


    @Test
    @Rollback(value = false)
    public void 아이템등록(){
        //given
        Item book = new Book();
        book.setName("스프링 2권");
        //when
        itemService.saveItem(book);


        //then
        assertEquals(book,itemRepository.findOne(book.getId()));

    }
}
