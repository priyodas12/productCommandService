package tech.springboot.productCommandService.controller;


import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import tech.springboot.productCommandService.model.Product;
import tech.springboot.productCommandService.model.ProductResponse;
import tech.springboot.productCommandService.service.ProductService;

@Log4j2
@RestController
@RequestMapping ("/api/v2")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping ("/products")
  public ResponseEntity<ProductResponse> createProduct (@Validated @RequestBody Product product) {
    log.info ("Creating product Id: {} ", product.getProductDesc ());
    ProductResponse productResponse = new ProductResponse ();
    productResponse.setProduct (productService.createProduct (product));
    productResponse.setStatusCode (HttpStatusCode.valueOf (200));
    return ResponseEntity.ok (productResponse);
  }

  @DeleteMapping ("/product/{productId}")
  public ResponseEntity<ProductResponse> deleteProduct (
      @Validated @PathVariable ("productId") BigInteger productId) {
    log.info ("Deleting product Id: {} ", productId);
    Optional<Product> productDeleted = productService.deleteProduct (productId);
    ProductResponse productResponse = new ProductResponse ();
    productResponse.setProduct (productDeleted.orElse (null));
    productResponse.setStatusCode (HttpStatusCode.valueOf (200));
    return ResponseEntity.ok (productResponse);
  }

  @PutMapping ("/products")
  public ResponseEntity<ProductResponse> updateProduct (@Validated @RequestBody Product product) {
    log.info ("Updating product Id: {} ", product.getProductId ());
    Optional<Product> productOptional = Optional.ofNullable (
        productService.updateProduct (product));
    ProductResponse productResponse = new ProductResponse ();
    productResponse.setProduct (productOptional.orElse (null));
    productResponse.setStatusCode (HttpStatusCode.valueOf (200));
    return ResponseEntity.ok (productResponse);
  }

}
