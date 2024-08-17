package tech.springboot.productCommandService.model;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table (name = "products")
public class Product {

  @Id
  @GeneratedValue (strategy = GenerationType.SEQUENCE)
  private BigInteger productId;

  private BigInteger productSerialNumber;


  private String productDesc;


  private BigDecimal productPrice;


  private String Origin;

  @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "America/Phoenix")
  private Date warrantyDate;

  @Override
  public boolean equals (Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass () != obj.getClass ()) {
      return false;
    }
    Product product = (Product) obj;
    return new EqualsBuilder ()
        .append (productId, product.productId)
        .append (productDesc, product.productDesc)
        .append (productPrice, product.productPrice)
        .append (productSerialNumber, product.productSerialNumber)
        .isEquals ();
  }

}
