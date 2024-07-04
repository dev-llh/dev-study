package yoongyu.studyjpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yoongyu.studyjpa.repository.*;
import yoongyu.studyjpa.vo.*;

@SpringBootTest
class MyStudyApplicationTests {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    void test() {
//        StoreVO storeVO = StoreVO.builder()
//                .storeNm("음식점1")
//                .address("경기도 용인시")
//                .president("누구누구")
//                .storeStatus("001001") //영업중?
//                .build();
//
//        storeRepository.save(storeVO);

        CategoryVO categoryVO = CategoryVO.builder()
                .categoryId(1)
                .categoryNm("양식")
                .storeId(4)
                .useYn("Y")
                .build();

        categoryRepository.save(categoryVO);

//        ItemVO itemVO = ItemVO.builder()
//                .storeId(4)
//                .itemNm("파스타")
//                .image(null)
//                .masterPrice(15000)
//                .categoryId(1)
//                .useYn("Y")
//                .build();
//
//        itemRepository.save(itemVO);

//        OrderVO orderVO = OrderVO.builder()
//                .storeId(4)
//                .orderStatus("002001") //접수중?
//                .orderType("003001") //배달?
//                .comment("양많이 주세요 ㅎㅎ")
//                .useYn("Y")
//                .build();
//
//        orderRepository.save(orderVO);

//        OrderItemVO orderItemVO = OrderItemVO.builder()
//                .itemId(1)
//                .orderId(1)
//                .count(2)
//                .price(15000)
//                .useYn("Y")
//                .build();
//
//        orderItemRepository.save(orderItemVO);


    }

}
