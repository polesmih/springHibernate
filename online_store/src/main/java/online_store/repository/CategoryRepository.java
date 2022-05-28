package online_store.repository;

import online_store.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findCategoryByTitleIgnoreCase(String title);
    List<Category> findCategoriesByParentCategoryIsNull();
}
