package net.karneim.pojobuilder.model;

import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

import java.util.EnumSet;

/**
 * Model for {@link net.karneim.pojobuilder.GeneratePojoBuilder#withFactoryMethod()}
 */
public class StaticFactoryMethodM extends MethodM {

  public StaticFactoryMethodM(String name) {
    super(name, EnumSet.of(PUBLIC, STATIC));
  }

}
