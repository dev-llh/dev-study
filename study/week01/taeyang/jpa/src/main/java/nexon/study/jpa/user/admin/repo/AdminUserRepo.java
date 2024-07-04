package nexon.study.jpa.user.admin.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nexon.study.jpa.core.jpa.JpaUtil;
import nexon.study.jpa.user.admin.entity.AdminUser;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminUserRepo {

    private final JpaUtil jpaUtil;

    public AdminUser saveAdminUser(AdminUser adminUser){
        EntityManager em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(adminUser);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            if(tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }
        return adminUser;
    }

    public AdminUser findById(Long id){

        EntityManager em = jpaUtil.getEntityManager();
        AdminUser adminUser = null;
        try {
            adminUser = em.find(AdminUser.class, id);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            em.close();
        }

        return adminUser;

    }

}
