package net.karneim.pojobuilder.processor.with.optionals;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.NoSuchElementException;

public abstract class OtherOptional<T> {
  public static <T> OtherOptional<T> empty() {
    return new OtherOptional<T>() {
      @Override
      public boolean isPresent() {
        return false;
      }

      @Override
      public T get() {
        throw new NoSuchElementException("No value present");
      }
    };
  }

  public static <T> OtherOptional<T> of(final T value) {
    checkNotNull(value, "value == null!");
    return new OtherOptional<T>() {
      @Override
      public boolean isPresent() {
        return true;
      }

      @Override
      public T get() {
        return value;
      }
    };
  }

  public abstract boolean isPresent();

  public abstract T get();
}
