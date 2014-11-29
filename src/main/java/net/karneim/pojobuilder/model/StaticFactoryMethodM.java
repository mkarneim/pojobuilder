package net.karneim.pojobuilder.model;

import javax.lang.model.element.Modifier;
import java.util.EnumSet;
import java.util.Set;

import static javax.lang.model.element.Modifier.*;

/**
 * Model for {@link net.karneim.pojobuilder.GeneratePojoBuilder#withFactoryMethod()}
 */
public class StaticFactoryMethodM extends MethodM {

  public StaticFactoryMethodM(String name) {
    super(name, EnumSet.of(PUBLIC, STATIC));
  }

}
