package com.example.amazone_database.Repository;

import com.example.amazone_database.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
   Product findProductById(int id);
}
