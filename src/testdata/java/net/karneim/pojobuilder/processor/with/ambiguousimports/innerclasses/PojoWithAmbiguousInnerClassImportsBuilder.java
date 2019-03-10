package net.karneim.pojobuilder.processor.with.ambiguousimports.innerclasses;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;
import net.karneim.pojobuilder.processor.with.ambiguousimports.innerclasses.pack1.Outer1.Inner;

@Generated("PojoBuilder")
public class PojoWithAmbiguousInnerClassImportsBuilder
    implements Cloneable {
  protected PojoWithAmbiguousInnerClassImportsBuilder self;
  protected Inner value$field1$net$karneim$pojobuilder$processor$with$ambiguousimports$innerclasses$pack1$Outer1$Inner;
  protected boolean isSet$field1$net$karneim$pojobuilder$processor$with$ambiguousimports$innerclasses$pack1$Outer1$Inner;
  protected net.karneim.pojobuilder.processor.with.ambiguousimports.innerclasses.pack2.Outer2.Inner value$field2$net$karneim$pojobuilder$processor$with$ambiguousimports$innerclasses$pack2$Outer2$Inner;
  protected boolean isSet$field2$net$karneim$pojobuilder$processor$with$ambiguousimports$innerclasses$pack2$Outer2$Inner;

  /**
   * Creates a new {@link PojoWithAmbiguousInnerClassImportsBuilder}.
   */
  public PojoWithAmbiguousInnerClassImportsBuilder() {
    self = (PojoWithAmbiguousInnerClassImportsBuilder)this;
  }

  /**
   * Sets the default value for the {@link PojoWithAmbiguousInnerClassImports#field1} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithAmbiguousInnerClassImportsBuilder withField1(Inner value) {
    this.value$field1$net$karneim$pojobuilder$processor$with$ambiguousimports$innerclasses$pack1$Outer1$Inner = value;
    this.isSet$field1$net$karneim$pojobuilder$processor$with$ambiguousimports$innerclasses$pack1$Outer1$Inner = true;
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithAmbiguousInnerClassImports#field2} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithAmbiguousInnerClassImportsBuilder withField2(net.karneim.pojobuilder.processor.with.ambiguousimports.innerclasses.pack2.Outer2.Inner value) {
    this.value$field2$net$karneim$pojobuilder$processor$with$ambiguousimports$innerclasses$pack2$Outer2$Inner = value;
    this.isSet$field2$net$karneim$pojobuilder$processor$with$ambiguousimports$innerclasses$pack2$Outer2$Inner = true;
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
      PojoWithAmbiguousInnerClassImportsBuilder result = (PojoWithAmbiguousInnerClassImportsBuilder)super.clone();
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
  public PojoWithAmbiguousInnerClassImportsBuilder but() {
    return (PojoWithAmbiguousInnerClassImportsBuilder)clone();
  }

  /**
   * Creates a new {@link PojoWithAmbiguousInnerClassImports} based on this builder's settings.
   *
   * @return the created PojoWithAmbiguousInnerClassImports
   */
  public PojoWithAmbiguousInnerClassImports build() {
    try {
      PojoWithAmbiguousInnerClassImports result = new PojoWithAmbiguousInnerClassImports();
      if (isSet$field1$net$karneim$pojobuilder$processor$with$ambiguousimports$innerclasses$pack1$Outer1$Inner) {
        result.field1 = value$field1$net$karneim$pojobuilder$processor$with$ambiguousimports$innerclasses$pack1$Outer1$Inner;
      }
      if (isSet$field2$net$karneim$pojobuilder$processor$with$ambiguousimports$innerclasses$pack2$Outer2$Inner) {
        result.field2 = value$field2$net$karneim$pojobuilder$processor$with$ambiguousimports$innerclasses$pack2$Outer2$Inner;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
