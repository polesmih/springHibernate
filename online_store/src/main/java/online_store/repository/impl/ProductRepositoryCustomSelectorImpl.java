package online_store.repository.impl;

import lombok.extern.slf4j.Slf4j;
import online_store.domain.Category;
import online_store.domain.FilterProductRequest;
import online_store.domain.Product;
import online_store.repository.ProductRepositoryCustomSelector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



@Slf4j
public class ProductRepositoryCustomSelectorImpl implements ProductRepositoryCustomSelector {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Product> getAllProductsFiltered(FilterProductRequest filterProductRequest, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = query.from(Product.class);


        List<Predicate> predicates = new ArrayList<>();

        Path<BigDecimal> costPath = productRoot.get("cost");
        Path<String> titlePath = productRoot.get("title");
        Path<Category> categoryPath = productRoot.get("category");


        BigDecimal minCost = filterProductRequest.getMinCost();
        if (minCost != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(costPath, minCost));
        }

        BigDecimal maxCost = filterProductRequest.getMaxCost();
        if (maxCost != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(costPath, maxCost));
        }

        String title = filterProductRequest.getTitle();
        if (title != null) {
            predicates.add(criteriaBuilder.like(titlePath, "%" + title + "%"));
        }

        Long categoryId = filterProductRequest.getCategoryId();
        if (categoryId != null) {
            predicates.add(criteriaBuilder.equal(categoryPath, categoryId));
        }

        CriteriaQuery<Product> select = query.select(productRoot).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        if (filterProductRequest.getSortDirection() == Sort.Direction.DESC) {
            select.orderBy(criteriaBuilder.desc(productRoot.get(filterProductRequest.getSortField())));
        } else {
            select.orderBy(criteriaBuilder.asc(productRoot.get(filterProductRequest.getSortField())));
        }


        TypedQuery<Product> typedQuery = entityManager.createQuery(select);

        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(Product.class)));
        countQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        Long count = entityManager.createQuery(countQuery).getSingleResult();


        return new PageImpl<> (typedQuery.getResultList(), pageable, count);
    }

}