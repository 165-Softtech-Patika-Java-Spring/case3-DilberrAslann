package com.homework3.homework3.app.dao;

import com.homework3.homework3.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
}
