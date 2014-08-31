package net.karneim.pojobuilder.processor.with.factorymethodannotation;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class ResourceFactory {
  @GeneratePojoBuilder
  public static Resource makeResource(final String name, final String content) {
    return new Resource() {
      
      @Override
      public String getName() {
        return name;
      }
      
      @Override
      public String getContent() {
        return content;
      }
    };
  }
}
