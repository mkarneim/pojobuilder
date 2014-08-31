package net.karneim.pojobuilder.processor.with.factorymethodannotation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class ContainerFactory {

  @GeneratePojoBuilder
  public static <X extends Number> Container<X> createContainer(final X content) {
    return new Container<X>() {

      @Override
      public X getContent() {
        return content;
      }

    };
  }

  @GeneratePojoBuilder(withName = "FileContainerBuilder")
  public static Container<File> createFileContainer(final File content) {
    return new Container<File>() {

      @Override
      public File getContent() {
        return content;
      }

    };
  }
  
  @GeneratePojoBuilder(withName = "GenericListContainerBuilder")
  public static <T> Container<List<T>> newGenericListContainer(final String content) {
    return new Container<List<T>>() {
      
      @Override
      public List<T> getContent() {
        ArrayList<T> result = new ArrayList<T>();        
        return result;
      }
    };
  }
}
