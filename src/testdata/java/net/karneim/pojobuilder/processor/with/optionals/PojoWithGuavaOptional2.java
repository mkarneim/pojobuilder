package net.karneim.pojobuilder.processor.with.optionals;

import com.google.common.base.Optional;
import net.karneim.pojobuilder.GeneratePojoBuilder;

import static net.karneim.pojobuilder.GeneratePojoBuilder.OptionalSupport.Guava;

@GeneratePojoBuilder(withOptionals = Guava)
public class PojoWithGuavaOptional2 {

  public Optional<Integer> optionalValue;

}
