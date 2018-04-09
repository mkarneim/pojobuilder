package net.karneim.pojobuilder.processor.with.ambiguousimports;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;
import net.karneim.pojobuilder.processor.with.ambiguousimports.pack1.Foo;

@Generated("PojoBuilder")
public class PojoBuilder
    implements Cloneable {
  protected PojoBuilder self;
  protected Foo value$foo1$net$karneim$pojobuilder$processor$with$ambiguousimports$pack1$Foo;
  protected boolean isSet$foo1$net$karneim$pojobuilder$processor$with$ambiguousimports$pack1$Foo;
  protected net.karneim.pojobuilder.processor.with.ambiguousimports.pack2.Foo value$foo2$net$karneim$pojobuilder$processor$with$ambiguousimports$pack2$Foo;
  protected boolean isSet$foo2$net$karneim$pojobuilder$processor$with$ambiguousimports$pack2$Foo;

  /**
   * Creates a new {@link PojoBuilder}.
   */
  public PojoBuilder() {
    self = (PojoBuilder)this;
  }

  /**
   * Sets the default value for the {@link Pojo#foo1} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder withFoo1(Foo value) {
    this.value$foo1$net$karneim$pojobuilder$processor$with$ambiguousimports$pack1$Foo = value;
    this.isSet$foo1$net$karneim$pojobuilder$processor$with$ambiguousimports$pack1$Foo = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo#foo2} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder withFoo2(net.karneim.pojobuilder.processor.with.ambiguousimports.pack2.Foo value) {
    this.value$foo2$net$karneim$pojobuilder$processor$with$ambiguousimports$pack2$Foo = value;
    this.isSet$foo2$net$karneim$pojobuilder$processor$with$ambiguousimports$pack2$Foo = true;
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
      PojoBuilder result = (PojoBuilder)super.clone();
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
  public PojoBuilder but() {
    return (PojoBuilder)clone();
  }

  /**
   * Creates a new {@link Pojo} based on this builder's settings.
   *
   * @return the created Pojo
   */
  public Pojo build() {
    try {
      Pojo result = new Pojo();
      if (isSet$foo1$net$karneim$pojobuilder$processor$with$ambiguousimports$pack1$Foo) {
        result.foo1 = value$foo1$net$karneim$pojobuilder$processor$with$ambiguousimports$pack1$Foo;
      }
      if (isSet$foo2$net$karneim$pojobuilder$processor$with$ambiguousimports$pack2$Foo) {
        result.foo2 = value$foo2$net$karneim$pojobuilder$processor$with$ambiguousimports$pack2$Foo;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
