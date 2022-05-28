package online_store.repository;

import online_store.domain.Category;
import online_store.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> , ProductRepositoryCustomSelector {

    List<Product> findAllByCategory(Category category);
}
