package nexon.study.jpa.core.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JpaUtil {

    private final EntityManagerFactory emf;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
