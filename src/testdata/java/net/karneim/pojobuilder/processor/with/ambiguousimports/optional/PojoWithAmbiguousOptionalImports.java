package net.karneim.pojobuilder.processor.with.ambiguousimports.optional;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withOptionalProperties=java.util.Optional.class)
public class PojoWithAmbiguousOptionalImports {
  public com.google.common.base.Optional<String> field;
}
