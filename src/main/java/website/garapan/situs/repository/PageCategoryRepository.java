package website.garapan.situs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import website.garapan.situs.model.PageCategory;

import java.util.List;

@Repository
public interface PageCategoryRepository extends JpaRepository<PageCategory, Long> {
    List<PageCategory> findByIsActiveTrueOrderByOrderAsc();

    @Query("SELECT pc FROM PageCategory pc LEFT JOIN FETCH pc.pages WHERE pc.isActive = true ORDER BY pc.order")
    List<PageCategory> findWithPagesByIsActiveTrueOrderByOrderAsc();
}