package samples;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class RecordsInUmbrellaClass {
  @GeneratePojoBuilder
  public record Foo(int foo) {};

  @GeneratePojoBuilder
  public record Bar(Foo foo) {};
  
  public record Ignore(int ignore) {};
}