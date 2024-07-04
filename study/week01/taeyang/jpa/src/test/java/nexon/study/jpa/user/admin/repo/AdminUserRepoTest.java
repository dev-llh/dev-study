package nexon.study.jpa.user.admin.repo;

import nexon.study.jpa.user.admin.entity.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminUserRepoTest {


    @Autowired
    private AdminUserRepo adminUserRepo;

    @Rollback(value = false)
    @Test
    void saveAdminUser(){
        AdminUser adminUser = adminUserRepo.saveAdminUser(AdminUser.builder()
                .userId("test")
                .password("test")
                .registerDate(LocalDateTime.now()).build());

        AdminUser findAdminUser = adminUserRepo.findById(1L);

        assertThat(adminUser.getUserId()).isEqualTo(findAdminUser.getUserId());
    }

    @Rollback(value = false)
    @Test
    void updateAdminUser(){
        AdminUser adminUser = adminUserRepo.saveAdminUser(AdminUser.builder()
                .userId("test")
                .password("test")
                .registerDate(LocalDateTime.now()).build());

        adminUser.setPassword("test1");

        AdminUser findAdminUser = adminUserRepo.findById(1L);

    }

}
