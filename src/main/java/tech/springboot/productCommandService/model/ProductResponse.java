package tech.springboot.productCommandService.model;


import org.springframework.http.HttpStatusCode;

import lombok.Data;

@Data
public class ProductResponse {

  Product product;
  HttpStatusCode statusCode;

}
