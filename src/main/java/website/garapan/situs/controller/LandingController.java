package website.garapan.situs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import website.garapan.situs.model.Article;
import website.garapan.situs.model.Page;
import website.garapan.situs.service.ArticleService;
import website.garapan.situs.service.PageService;

import java.util.List;

@Controller
public class LandingController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private PageService pageService;

    @GetMapping("/")
    public String landingPage(Model model) {
        // Add featured articles to the model
        List<Article> featuredArticles = articleService.getFeaturedArticles();
        model.addAttribute("featuredArticles", featuredArticles);

        // Add latest published articles
        List<Article> latestArticles = articleService.getPublishedArticles();
        model.addAttribute("latestArticles", latestArticles);

        // Add most viewed articles
        List<Article> mostViewed = articleService.getMostViewedArticles(5);
        model.addAttribute("mostViewedArticles", mostViewed);

        // Add page categories for navigation
        List<website.garapan.situs.model.PageCategory> categories = pageService.getAllActiveCategories();
        model.addAttribute("categories", categories);

        return "landing";
    }

    @GetMapping("/article/{id}")
    public String viewArticle(@PathVariable Long id, Model model) {
        Article article = articleService.getArticleById(id);
        if (article == null) {
            return "error/404";
        }

        // Increment view count
        articleService.incrementViewCount(id);

        model.addAttribute("article", article);

        // Add related articles
        List<Article> relatedArticles = articleService.getRelatedArticles(id, 5);
        model.addAttribute("relatedArticles", relatedArticles);

        // Add most viewed articles for sidebar
        List<Article> mostViewed = articleService.getMostViewedArticles(5);
        model.addAttribute("mostViewedArticles", mostViewed);

        // Add page categories for navigation
        List<website.garapan.situs.model.PageCategory> categories = pageService.getAllActiveCategories();
        model.addAttribute("categories", categories);

        return "article";
    }

    @GetMapping("/page/{slug}")
    public String viewPage(@PathVariable String slug, Model model) {
        Page page = pageService.getPageBySlug(slug);
        if (page == null) {
            return "error/404";
        }

        model.addAttribute("page", page);

        // Add page categories for navigation
        List<website.garapan.situs.model.PageCategory> categories = pageService.getAllActiveCategories();
        model.addAttribute("categories", categories);

        return "page";
    }

    @GetMapping("/articles")
    public String articlesList(Model model) {
        List<Article> articles = articleService.getPublishedArticles();
        model.addAttribute("articles", articles);

        // Add page categories for navigation
        List<website.garapan.situs.model.PageCategory> categories = pageService.getAllActiveCategories();
        model.addAttribute("categories", categories);

        return "articles";
    }
}
