package website.garapan.situs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.garapan.situs.model.Article;
import website.garapan.situs.repository.ArticleRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllActiveArticles() {
        return articleRepository.findByIsActiveTrue();
    }

    public List<Article> getFeaturedArticles() {
        return articleRepository.findByIsFeaturedTrue();
    }

    public List<Article> getPublishedArticles() {
        return articleRepository.findByPublishedAtBeforeAndIsActiveTrue(LocalDateTime.now());
    }

    public List<Article> getMostViewedArticles(int limit) {
        List<Article> articles = articleRepository.findTop10ByOrderByViewsCountDesc();
        return articles.size() > limit ? articles.subList(0, limit) : articles;
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article saveArticle(Article article) {
        // If publishedAt is null, set it to current time
        if (article.getPublishedAt() == null) {
            article.setPublishedAt(LocalDateTime.now());
        }
        return articleRepository.save(article);
    }

    public void incrementViewCount(Long id) {
        Article article = getArticleById(id);
        if (article != null) {
            article.setViewsCount(article.getViewsCount() + 1);
            articleRepository.save(article);
        }
    }

    public List<Article> getRelatedArticles(Long articleId, int limit) {
        Article currentArticle = getArticleById(articleId);
        if (currentArticle == null) {
            return List.of();
        }

        // For now, return other published articles excluding the current one
        List<Article> allPublished = getPublishedArticles();
        return allPublished.stream()
                .filter(article -> !article.getId().equals(articleId))
                .limit(limit)
                .toList();
    }
}