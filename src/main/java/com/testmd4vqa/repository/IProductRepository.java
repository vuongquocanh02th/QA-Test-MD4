package com.testmd4vqa.repository;

import com.testmd4vqa.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    @Query("SELECT p FROM Product p WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:category IS NULL OR p.category.cId = :category) AND " +
            "(:status IS NULL OR LOWER(p.status) LIKE LOWER(CONCAT('%', :status, '%')))")
    Page<Product> searchProducts(
            @Param("name") String name,
            @Param("category") Long category,
            @Param("status") String status,
            Pageable pageable);
}
