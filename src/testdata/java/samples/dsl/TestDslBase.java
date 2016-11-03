package samples.dsl;

import static com.google.common.collect.Maps.newHashMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import org.assertj.core.api.Assertions;

import samples.Builder;

/**
 * This is the domain-agnostic basis for the domain-specific language for writing test.
 * <p>
 * See {@link TestDslTest} to see it in action.
 */
public class TestDslBase extends Assertions {

  public static final String PACKAGE = "samples.dsl";

  static class PojoFactory {
    @GeneratePojoBuilder(intoPackage = PACKAGE, withBuilderInterface = Builder.class, withBuilderProperties = true)
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

  public static int few() {
    return 3;
  }

  public static int several() {
    return 10;
  }

  public static int many() {
    return 100;
  }

  public static <P> P some(Builder<P> $builder) {
    return $builder.build();
  }

  public static <P> P a(Builder<P> $builder) {
    return $builder.build();
  }

  public static <P> P an(Builder<P> $builder) {
    return $builder.build();
  }

  @SafeVarargs
  public static <T> List<T> listOf(T... elems) {
    return Arrays.asList(elems);
  }


  @SafeVarargs
  public static <T> List<T> listOf(List<T>... lists) {
    List<T> result = new ArrayList<T>();
    for (List<T> l : lists) {
      result.addAll(l);
    }
    return result;
  }

  @SafeVarargs
  public static <T> List<T> listOf(List<T> list, T... elems) {
    List<T> result = new ArrayList<T>(list);
    for (T elem : elems) {
      result.add(elem);
    }
    return result;
  }

  public static <T> List<T> listOf(int count, Builder<T> $builder) {
    List<T> result = new ArrayList<T>();
    for (int i = 0; i < count; i++) {
      result.add(some($builder));
    }
    return result;
  }

  @SafeVarargs
  public static <T> Builder<List<T>> $listOf(final Builder<? extends T>... elements) {
    return new Builder<List<T>>() {

      @Override
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
      @Override
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
  public static <T> Builder<T> $oneOf(final T... elements) {
    return new Builder<T>() {
      int idx = 0;

      @Override
      public T build() {
        if (idx > elements.length - 1) {
          idx = 0;
        }
        return elements[idx++];
      }
    };
  }

  public static <T> Builder<T> $oneOf(final List<T> elements) {
    return new Builder<T>() {
      int idx = 0;

      @Override
      public T build() {
        if (idx > elements.size() - 1) {
          idx = 0;
        }
        return elements.get(idx++);
      }
    };
  }

  @SafeVarargs
  public static <T> Builder<T> $oneOf(final Builder<? extends T>... elements) {
    return new Builder<T>() {
      int idx = 0;

      @Override
      public T build() {
        if (idx > elements.length - 1) {
          idx = 0;
        }
        T result = (T) some(elements[idx++]);
        return result;
      }
    };
  }

  @SafeVarargs
  public static <T> T oneOf(final T... elements) {
    return $oneOf(elements).build();
  }

  @SafeVarargs
  public static <T> T oneOf(final Builder<? extends T>... elements) {
    return $oneOf(elements).build();
  }

  @SuppressWarnings("unchecked")
  public static <T> T as(Class<T> cls, Object entity) {
    return (T) entity;
  }

  @SafeVarargs
  public static <K, V> Map<K, V> mapOf(Map.Entry<K, V>... entries) {
    Map<K, V> result = newHashMap();
    for (Map.Entry<K, V> entry : entries) {
      result.put(entry.getKey(), entry.getValue());
    }
    return result;
  }

  public static <K, V> Map.Entry<K, V> entryOf(final K key, final V value) {
    Entry<K, V> entry = new Map.Entry<K, V>() {
      private V theValue = value;

      @Override
      public K getKey() {
        return key;
      }

      @Override
      public V getValue() {
        return theValue;
      }

      @Override
      public V setValue(V value) {
        V old = theValue;
        theValue = value;
        return old;
      }
    };
    return entry;
  }

}
