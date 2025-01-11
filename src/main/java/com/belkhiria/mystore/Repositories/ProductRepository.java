package com.belkhiria.mystore.Repositories;

import com.belkhiria.mystore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
