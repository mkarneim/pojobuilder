package net.karneim.pojobuilder.testenv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

/**
 * @author Adrodoc55
 */
public class TestDslBase {
  private static final Random RANDOM = new Random(5);
  private static final IntBuilder $int = new IntBuilder(RANDOM);

  public static <P> P some(Builder<P> builder) {
    return builder.build();
  }

  public static IntBuilder $int() {
    return $int;
  }

  public static Builder<String> $String() {
    return new Builder<String>() {
      @Override
      public String build() {
        return "String" + some($int());
      }
    };
  }

  public static Builder<Boolean> $boolean() {
    return new Builder<Boolean>() {
      @Override
      public Boolean build() {
        return RANDOM.nextBoolean();
      }
    };
  }

  public static int few() {
    return some($int().between(2, 4));
  }

  public static int several() {
    return some($int().between(5, 10));
  }

  public static int many() {
    return some($int().between(11, 100));
  }

  @SafeVarargs
  public static <C> Builder<C> $oneOf(C... choices) {
    return $oneOf(Arrays.asList(choices));
  }

  public static <C> Builder<C> $oneOf(Iterable<C> choices) {
    return new OneOf<C>(choices);
  }

  public static class OneOf<C> implements Builder<C> {
    private final Map<C, Integer> counter;
    private final Iterator<C> it;

    public OneOf(Iterable<C> choices) {
      int size = Iterables.size(choices);
      counter = new IdentityHashMap<C, Integer>(size);
      it = Iterators.cycle(choices);
      Iterators.advance(it, some($int().between(0, size)));
    }

    public int getCount(C choice) {
      Integer integer = counter.get(choice);
      if (integer == null)
        return 0;
      else
        return integer;
    }

    @Override
    public C build() {
      C next = it.next();
      counter.put(next, getCount(next) + 1);
      return next;
    }

  }

  public static <P> Builder<List<P>> $listOf(final int count, final Builder<P> prototype) {
    return new Builder<List<P>>() {
      @Override
      public List<P> build() {
        return listOf(count, prototype);
      }
    };
  }

  @SafeVarargs
  public static <E> List<E> listOf(E... elements) {
    return Arrays.asList(elements);
  }

  public static <P> List<P> listOf(int count, Builder<P> prototype) {
    List<P> list = new ArrayList<P>();
    for (int i = 0; i < count; i++) {
      list.add(prototype.build());
    }
    return list;
  }

}
