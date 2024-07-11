package yoongyu.studyjpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import yoongyu.studyjpa.core.Config;
import yoongyu.studyjpa.repository.*;
import yoongyu.studyjpa.vo.*;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class MyStudyApplicationTests {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderContentRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private Config config;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    void createTest() {
        StoreVO storeVO = StoreVO.builder()
                .storeNm("음식점3")
                .address("경기도 용인시")
                .president("3333")
                .storeStatus("001001") //영업중?
                .useYn("Y")
                .build();

        storeRepository.save(storeVO);

//        CategoryVO categoryVO = CategoryVO.builder()
//                .categoryId(1L)
//                .categoryNm("양식")
//                .storeId(1L)
//                .useYn("Y")
//                .build();
//
//        categoryRepository.save(categoryVO);
//
//
//        ItemVO itemVO = ItemVO.builder()
//                .storeId(1L)
//                .itemNm("파스타")
//                .image(null)
//                .masterPrice(15000)
//                .categoryId(1L)
//                .useYn("Y")
//                .build();
//
//        itemRepository.save(itemVO);
//
//        OrderContentVO orderVO = OrderContentVO.builder()
//                .storeId(1L)
//                .orderStatus("002001") //접수중?
//                .orderType("003001") //배달?
//                .detail("추가사항없음")
//                .orderDtt(LocalDateTime.now())
//                .useYn("Y")
//                .build();
//
//        orderRepository.save(orderVO);
//
//        OrderItemVO orderItemVO = OrderItemVO.builder()
//                .itemId(1L)
//                .orderId(1L)
//                .count(2)
//                .price(15000)
//                .useYn("Y")
//                .build();
//
//        orderItemRepository.save(orderItemVO);


    }

    @Test
    @Transactional
    @Rollback(value = false)
    void persistenceTest() {
        EntityManager em = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        StoreVO storeVO = entityManager.find(StoreVO.class, 1);
        System.out.println(storeVO);

        storeVO.setStoreNm("음식점1");
        System.out.println(storeVO);

        transaction.commit();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void persistenceTest2() {
        StoreVO storeVO = entityManager.find(StoreVO.class, 2);
        System.out.println(storeVO);

        storeVO.setStoreNm("음식점2");
        System.out.println(storeVO);

        entityManager.persist(storeVO);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void persistenceTest3() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        StoreVO storeVO = em.find(StoreVO.class, 3);
        System.out.println(storeVO);

        storeVO.setPresident("음식점 3의 대표");
        em.merge(storeVO);

        em.getTransaction().commit();

        System.out.println(storeVO);

    }

    @Test
    void selectTest() {
        StoreVO storeVO = StoreVO.builder()
                .storeNm("음식점1")
                .address("경기도 용인시")
                .president("누구누구")
                .storeStatus("001001") //영업중?
                .useYn("Y")
                .build();

        storeRepository.save(storeVO);

        TypedQuery<StoreVO> query = entityManager.createQuery(
                "select s from StoreVO s", StoreVO.class);
        List<StoreVO> storeVOList = query.getResultList();

        System.out.println(storeVOList);
    }

}
