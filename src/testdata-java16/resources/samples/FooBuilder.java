package samples;

import javax.annotation.processing.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class FooBuilder
    implements Cloneable {
  protected FooBuilder self;
  protected int value$foo$int;
  protected boolean isSet$foo$int;

  /**
   * Creates a new {@link FooBuilder}.
   */
  public FooBuilder() {
    self = (FooBuilder)this;
  }

  /**
   * Sets the default value for the foo property.
   *
   * @param value the default value
   * @return this builder
   */
  public FooBuilder withFoo(int value) {
    this.value$foo$int = value;
    this.isSet$foo$int = true;
    return self;
  }

  /**
   * Returns a clone of this builder.
   *
   * @return the clone
   */
  @Override
  @GwtIncompatible
  public Object clone() {
    try {
      FooBuilder result = (FooBuilder)super.clone();
      result.self = result;
      return result;
    } catch (CloneNotSupportedException e) {
      throw new InternalError(e.getMessage());
    }
  }

  /**
   * Returns a clone of this builder.
   *
   * @return the clone
   */
  @GwtIncompatible
  public FooBuilder but() {
    return (FooBuilder)clone();
  }

  /**
   * Creates a new {@link RecordsInUmbrellaClass.Foo} based on this builder's settings.
   *
   * @return the created RecordsInUmbrellaClass.Foo
   */
  public RecordsInUmbrellaClass.Foo build() {
    try {
      RecordsInUmbrellaClass.Foo result = new RecordsInUmbrellaClass.Foo(value$foo$int);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
