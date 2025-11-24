package website.garapan.situs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.garapan.situs.model.Page;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
    List<Page> findByIsActiveTrue();
    List<Page> findByCategory_IdAndIsActiveTrue(Long categoryId);
    Optional<Page> findBySlugAndIsActiveTrue(String slug);
}