package samples.dsl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import org.assertj.core.api.Assertions;

import samples.Builder;

/**
 * This is the domain-agnositic basis for the domain-specific language.
 * <p>
 * See {@link DslTest} to see it in action.
 */
public class DslBase extends Assertions {

  public static final String PACKAGE = "samples.dsl";

  static class PojoFactory {
    @GeneratePojoBuilder(intoPackage = PACKAGE, withBuilderInterface = Builder.class,
        withBuilderProperties = true)
    public static String createString(String format, long nextNumber) {
      return String.format(format, nextNumber);
    }

  }

  private static final LongBuilder $Long = new LongBuilder(1);
  private static final IntegerBuilder $Integer = new IntegerBuilder(1);

  public static LongBuilder $Long() {
    return $Long;
  }

  public static IntegerBuilder $Integer() {
    return $Integer;
  }

  public static StringBuilder $String() {
    return new StringBuilder().withFormat("string-%s").withNextNumber($Long());
  }

  public static <P> P some(Builder<P> $builder) {
    return $builder.build();
  }

  @SafeVarargs
  public static <T> List<T> listOf(T... elems) {
    return Arrays.asList(elems);
  }

  @SafeVarargs
  public static <T> Builder<List<T>> $listOf(final Builder<? extends T>... elements) {
    return new Builder<List<T>>() {

      public List<T> build() {
        List<T> result = new ArrayList<T>(elements.length);
        for (Builder<? extends T> elementBuilder : elements) {
          result.add((T) elementBuilder.build());
        }
        return result;
      }
    };
  }

  public static <P> Builder<List<P>> $listOf(final int number, final Builder<P> prototype) {
    return new Builder<List<P>>() {
      public List<P> build() {
        List<P> result = new ArrayList<P>();
        for (int i = 0; i < number; ++i) {
          result.add(some(prototype));
        }
        return result;
      }
    };
  }

  @SafeVarargs
  public static <T> Builder<T> $from(final T... elements) {
    return new Builder<T>() {
      int idx = 0;

      public T build() {
        if (idx > elements.length - 1) {
          idx = 0;
        }
        return elements[idx++];
      }
    };
  }

  @SafeVarargs
  public static <T> Builder<T> $from(final Builder<? extends T>... elements) {
    return new Builder<T>() {
      int idx = 0;

      public T build() {
        if (idx > elements.length - 1) {
          idx = 0;
        }
        T result = (T) some(elements[idx++]);
        return result;
      }
    };
  }

}
