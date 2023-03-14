package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Fail.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;





    //주문
    @Test
    public void 주문(){

        //given
        Member member = getMember();

        Book book = getBook();

        //when
        long orderId= orderService.order(member.getId(), book.getId(), 2);

        //then


        Order getOrder = orderRepository.findOne(orderId);
        Assert.assertEquals("주문 상태", OrderStatus.ORDER,getOrder.getStatus());
        Assert.assertEquals("주문한 상품 종류",1,getOrder.getOrderItems().size());
        Assert.assertEquals("주문가격은 가격 * 수량",10000*2,getOrder.getTotalPrice());
        Assert.assertEquals("수량만큼 재고가 줄어야한다",8,book.getStockQuantity());



    }


    //주문취소
    public void 주문취소(){
        Member member = getMember();
        Book book = getBook();

        Long orderId= orderService.order(member.getId(),book.getId(),2);

        orderService.cancelOrder(orderId);
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("주문취소상태",OrderStatus.CANCLE,getOrder.getStatus());
        Assert.assertEquals("주문취소면 재고가 다시 증가",10,book.getStockQuantity());

    }

    //주문 수량 초과 테스트
    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception {

        Member member=getMember();
        Book book = getBook();

        orderService.order(member.getId(),book.getId(),11);

        fail("재고 수량 부족 예외가 발생해야함");
    }


    //

    private Book getBook() {
        Book book = new Book();

        book.setName("스프링완전정복");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        return book;
    }


    private Member getMember() {
        Member member = new Member();
        member.setName("회원 1");
        member.setAddress(new Address("서울","강남","122"));
        em.persist(member);
        return member;
    }



}
