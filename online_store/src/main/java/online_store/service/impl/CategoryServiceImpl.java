package online_store.service.impl;


import lombok.RequiredArgsConstructor;
import online_store.domain.Category;
import online_store.repository.CategoryRepository;
import online_store.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getAllWithoutParents() {
        return categoryRepository.findCategoriesByParentCategoryIsNull();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findCategoryByTitleIgnoreCase(title).stream().findFirst();
    }

    @Override
    public Category saveOrUpdate(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}