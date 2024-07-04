package yoongyu.studyjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoongyu.studyjpa.vo.CategoryVO;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryVO, Long> {
}
