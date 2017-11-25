package net.karneim.pojobuilder;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is a copy of the GwtIncompatible annotation provided by Google.
 * <p>
 * Any class, method or field with an annotation @GwtIncompatible (with any package prefix) is
 * ignored by the GWT compiler.
 * <p>
 * As mentioned at <a href=
 * "http://www.gwtproject.org/javadoc/latest/com/google/gwt/core/shared/GwtIncompatible.html">GwtIncompatible.html</a>,
 * third-party libraries may use their own copy of this annotation class to avoid adding a
 * compile-time dependency on GWT.
 * <p>
 * Please note that the retention policy of this annotation is "CLASS", which means, that you can
 * ship generated builder classes without the need to include this annotation into the target
 * application's classpath.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
@Documented
public @interface GwtIncompatible {
  /**
   * @return An attribute that can be used to explain why the code is incompatible. A
   *         GwtIncompatible annotation can have any number of attributes; attributes are for
   *         documentation purposes and are ignored by the GWT compiler.
   */
  String value() default "";
}
