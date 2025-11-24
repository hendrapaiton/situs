package website.garapan.situs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.garapan.situs.model.Page;
import website.garapan.situs.model.PageCategory;
import website.garapan.situs.repository.PageRepository;
import website.garapan.situs.repository.PageCategoryRepository;

import java.util.List;

@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;
    
    @Autowired
    private PageCategoryRepository pageCategoryRepository;

    public List<Page> getAllActivePages() {
        return pageRepository.findByIsActiveTrue();
    }

    public Page getPageBySlug(String slug) {
        return pageRepository.findBySlugAndIsActiveTrue(slug).orElse(null);
    }

    public Page savePage(Page page) {
        return pageRepository.save(page);
    }

    public List<PageCategory> getAllActiveCategories() {
        return pageCategoryRepository.findWithPagesByIsActiveTrueOrderByOrderAsc();
    }

    public PageCategory getCategoryById(Long id) {
        return pageCategoryRepository.findById(id).orElse(null);
    }

    public PageCategory saveCategory(PageCategory category) {
        return pageCategoryRepository.save(category);
    }
}