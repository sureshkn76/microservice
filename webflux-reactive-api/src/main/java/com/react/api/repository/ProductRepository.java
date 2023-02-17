package com.react.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.react.api.model.Product;


@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
