package net.karneim.pojobuilder.processor.with.factorymethodannotation;

import java.math.BigDecimal;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class ProductFactory {
  
  @GeneratePojoBuilder
  public static Product makeProduct( String name, BigDecimal price) {
    return new Product(name, price);
  }
}
