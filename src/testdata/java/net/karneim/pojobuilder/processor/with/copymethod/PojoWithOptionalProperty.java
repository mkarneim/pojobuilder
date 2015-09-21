package net.karneim.pojobuilder.processor.with.copymethod;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import com.google.common.base.Optional;

public class PojoWithOptionalProperty {
  public final Optional<String> prop;

  @GeneratePojoBuilder(withCopyMethod = true)
  public PojoWithOptionalProperty(String prop) {
    this.prop = Optional.of(prop);
  }

}
