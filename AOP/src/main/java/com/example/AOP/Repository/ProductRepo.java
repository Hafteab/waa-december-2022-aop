package com.example.AOP.Repository;

import com.example.AOP.Entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends CrudRepository<Product,Integer> {
    List<Product> findAllByPriceGreaterThan(double minPrice);


    List<Product> findAllByNameContaining(String name);
}
