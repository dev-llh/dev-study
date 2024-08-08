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

import javax.crypto.spec.PSource;

import static org.junit.Assert.*;

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
    @Transactional
    @Rollback(value = false)
    void createTest() {
        StoreVO storeVO = StoreVO.builder()
                .storeNm("음식점1")
                .address("경기도 용인시")
                .president("1111")
                .storeStatus("001001") //영업중?
                .useYn("Y")
                .build();

        storeRepository.save(storeVO);

        CategoryVO categoryVO = CategoryVO.builder()
                .categoryNm("음식점1_양식")
                .storeVO(storeVO)
                .useYn("Y")
                .build();

        categoryRepository.save(categoryVO);


        ItemVO itemVO = ItemVO.builder()
                .storeVO(storeVO)
                .itemNm("음식점1_파스타")
                .image(null)
                .masterPrice(15000)
                .categoryVO(categoryVO)
                .useYn("Y")
                .build();

        itemRepository.save(itemVO);

        OrderContentVO orderContentVO = OrderContentVO.builder()
                .storeVO(storeVO)
                .orderStatus("002001") //접수중?
                .orderType("003001") //배달?
                .detail("추가사항없음")
                .orderDtt(LocalDateTime.now())
                .useYn("Y")
                .build();

        orderRepository.save(orderContentVO);

        OrderItemVO orderItemVO = OrderItemVO.builder()
                .orderContentVO(orderContentVO)
                .count(2)
                .price(15000)
                .useYn("Y")
                .build();

        orderItemRepository.save(orderItemVO);


    }

//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void persistenceTest() {
//        EntityManager em = entityManagerFactory.createEntityManager();
//
//        EntityTransaction transaction = em.getTransaction();
//
//        transaction.begin();
//        StoreVO storeVO = entityManager.find(StoreVO.class, 1);
//        System.out.println(storeVO);
//
//        storeVO.setStoreNm("수정된음식점1");
//        System.out.println(storeVO);
//
//        transaction.commit();
//    }

//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void persistenceTest2() {
//        StoreVO storeVO = entityManager.find(StoreVO.class, 2);
//        System.out.println(storeVO);
//
//        entityManager.detach(storeVO);
//
//        storeVO.setStoreNm("수정된음식점2");
//        System.out.println(storeVO);
//
//
//    }

//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void persistenceTest3() {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        em.getTransaction().begin();
//
//        StoreVO storeVO = em.find(StoreVO.class, 3);
//        System.out.println(storeVO);
//
//        storeVO.setStoreNm("수정된음식점3");
//        em.merge(storeVO);
//
//        em.getTransaction().commit();
//
//        System.out.println(storeVO);
//
//    }

//    @Test
//    void selectTest() {
//        StoreVO storeVO = StoreVO.builder()
//                .storeNm("음식점1")
//                .address("경기도 용인시")
//                .president("누구누구")
//                .storeStatus("001001") //영업중?
//                .useYn("Y")
//                .build();
//
//        storeRepository.save(storeVO);
//
//        TypedQuery<StoreVO> query = entityManager.createQuery(
//                "select s from StoreVO s", StoreVO.class);
//        List<StoreVO> storeVOList = query.getResultList();
//
//        System.out.println(storeVOList);
//    }

//    @Test
//    void joinTest() {
//        CategoryVO categoryVO = entityManager.find(CategoryVO.class, 1);
//        System.out.println(categoryVO.getStoreVO().getStoreNm());
//
//        StoreVO storeVO = entityManager.find(StoreVO.class, 1);
//        System.out.println(storeVO);
//    }

    @Test
    void tddTest1() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        StoreVO storeVO = null; //RED

        assertEquals(true, storeVO != null);
        System.out.println("success");

        transaction.commit();
    }

    @Test
    void tddTest2() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        StoreVO storeVO = entityManager.find(StoreVO.class, 1); //GREEN

        assertEquals(true, storeVO != null);
        System.out.println("success");

        transaction.commit();
    }

    @Test
    @Transactional
    void tddTest3() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        StoreVO storeVO = em.find(StoreVO.class, 1);
        System.out.println(storeVO);

        storeVO.setStoreNm("수정된 음식점1");

        em.flush();
        em.clear();

        StoreVO storeVO2 = em.find(StoreVO.class, 1);
        System.out.println(storeVO2);

        assertEquals(true, "수정된 음식점1".equals(storeVO2.getStoreNm()));
        System.out.println("success");

        transaction.commit();
    }

    @Test
    void tddTest4() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        String modifiedStoreNm = "수정된 음식점1";

        StoreVO storeVO = em.find(StoreVO.class, 1);
        storeVO.setStoreNm(modifiedStoreNm);

        em.flush();
        em.clear();

        StoreVO storeVO2 = em.find(StoreVO.class, 1);

        assertEquals(true, modifiedStoreNm.equals(storeVO2.getStoreNm()));
        transaction.commit();
    }

    @Test
    void tddJoinTest() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        CategoryVO categoryVO = em.find(CategoryVO.class, 1);
        assertEquals(true, "음식점1".equals(categoryVO.getStoreVO().getStoreNm()));
        System.out.println("success");

        transaction.commit();
    }
}
