package tech.springboot.productCommandService.service;


import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import tech.springboot.productCommandService.model.EventType;
import tech.springboot.productCommandService.model.Product;
import tech.springboot.productCommandService.repository.ProductRepository;

@Service
@Log4j2
public class ProductService {


  @Autowired
  private ProductEventService productEventService;

  @Autowired
  private ProductRepository productRepository;

  public Product createProduct (Product product) {
    log.info ("Creating product : {} ", product);
    Product savedProduct = productRepository.save (product);
    productEventService.publishToSqs (savedProduct, EventType.CREATE_EVENT.name ());
    return savedProduct;
  }

  public Product updateProduct (Product product) {
    log.info ("Updating product Id: {} ", product.getProductId ());
    Optional<Product> productDb = productRepository.findById (product.getProductId ());
    if (productDb.isPresent ()) {
      if (!productDb.get ().equals (product)) {
        Product updatedProduct = productRepository.save (product);
        productEventService.publishToSqs (updatedProduct, EventType.UPDATE_EVENT.name ());
        return updatedProduct;
      }
    }
    return null;
  }

  public Optional<Product> deleteProduct (BigInteger productId) {
    log.info ("Deleting product Id: {} ", productId);
    Optional<Product> productDb = productRepository.findById (productId);
    if (productDb.isPresent ()) {
      Product deleteProduct = productDb.get ();
      productRepository.delete (deleteProduct);
      productEventService.publishToSqs (deleteProduct, EventType.DELETE_EVENT.name ());
      return Optional.of (deleteProduct);
    }
    return Optional.empty ();
  }


}
