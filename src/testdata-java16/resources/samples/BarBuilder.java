package samples;

import javax.annotation.processing.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class BarBuilder
    implements Cloneable {
  protected BarBuilder self;
  protected RecordsInUmbrellaClass.Foo value$foo$samples$RecordsInUmbrellaClass$Foo;
  protected boolean isSet$foo$samples$RecordsInUmbrellaClass$Foo;

  /**
   * Creates a new {@link BarBuilder}.
   */
  public BarBuilder() {
    self = (BarBuilder)this;
  }

  /**
   * Sets the default value for the foo property.
   *
   * @param value the default value
   * @return this builder
   */
  public BarBuilder withFoo(RecordsInUmbrellaClass.Foo value) {
    this.value$foo$samples$RecordsInUmbrellaClass$Foo = value;
    this.isSet$foo$samples$RecordsInUmbrellaClass$Foo = true;
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
      BarBuilder result = (BarBuilder)super.clone();
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
  public BarBuilder but() {
    return (BarBuilder)clone();
  }

  /**
   * Creates a new {@link RecordsInUmbrellaClass.Bar} based on this builder's settings.
   *
   * @return the created RecordsInUmbrellaClass.Bar
   */
  public RecordsInUmbrellaClass.Bar build() {
    try {
      RecordsInUmbrellaClass.Bar result = new RecordsInUmbrellaClass.Bar(value$foo$samples$RecordsInUmbrellaClass$Foo);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
