package website.garapan.situs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.garapan.situs.model.Article;

import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByIsActiveTrue();
    List<Article> findByIsFeaturedTrue();
    List<Article> findByPublishedAtBefore(LocalDateTime dateTime);
    List<Article> findByPublishedAtBeforeAndIsActiveTrue(LocalDateTime dateTime);
    List<Article> findTop10ByOrderByViewsCountDesc();
}