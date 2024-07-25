package kr.taeu.jpapractice;

import jakarta.persistence.EntityManager;
import kr.taeu.jpapractice.onetoone.ChildEntity;
import kr.taeu.jpapractice.onetoone.ChildRepository;
import kr.taeu.jpapractice.onetoone.ParentEntity;
import kr.taeu.jpapractice.onetoone.ParentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(JpaConfig.class)
@TestPropertySource(properties = {
        "logging.level.org.hibernate.orm.jdbc.bind=trace"
})
public class OneToOneTest {

    @Autowired
    EntityManager em;

    @Autowired
    ParentRepository parentRepository;

    @Autowired
    ChildRepository childRepository;

    @Test
    void oneToOneTest() {
        ParentEntity parentEntity = ParentEntity.builder().build();
        ChildEntity childEntity = ChildEntity.builder().build();
        parentEntity.setChild(childEntity);
        childEntity.setParent(parentEntity);

        em.persist(parentEntity);
        em.flush();

        System.out.println("childEntity = " + childEntity.getId() + ", parentEntity = " + childEntity.getParent().getId());
        System.out.println("parentEntity = " + parentEntity.getId() + ", childEntity = " + parentEntity.getChild().getId());

        em.remove(parentEntity);
        em.flush();
    }

    @Test
    void springJpaTestParent() {
        ParentEntity parentEntity = ParentEntity.builder().build();
        ChildEntity childEntity = ChildEntity.builder().build();
        parentEntity.setChild(childEntity);
        childEntity.setParent(parentEntity);

        parentRepository.save(parentEntity);

        em.flush();

        System.out.println("childEntity = " + childEntity.getId() + ", parentEntity = " + childEntity.getParent().getId());
        System.out.println("parentEntity = " + parentEntity.getId() + ", childEntity = " + parentEntity.getChild().getId());

        parentRepository.delete(parentEntity);

        em.flush();
    }

    @Test
    void springJpaTestChild() {
        ParentEntity parentEntity = ParentEntity.builder().build();
        ChildEntity childEntity = ChildEntity.builder().build();
        childEntity.setParent(parentEntity);

        childRepository.save(childEntity);

        em.flush();
        em.clear();

        System.out.println("childEntity = " + childEntity.getId() + ", parentEntity = " + childEntity.getParent().getId());
        System.out.println("parentEntity = " + parentEntity.getId());

        childRepository.delete(childEntity);

        em.flush();
    }
}
