package tech.springboot.productCommandService.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.springboot.productCommandService.model.Product;

public interface ProductRepository extends JpaRepository<Product, BigInteger> {

}
