package javaxfake.annotation;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * This annotation fakes the @Generated annotation from Java, that has in Java 9 been moved from
 * javax.annotation.Generated to javax.annotation.processing.Generated.
 */
@Documented
@Retention(SOURCE)
@Target({PACKAGE, TYPE, METHOD, CONSTRUCTOR, FIELD, LOCAL_VARIABLE, PARAMETER})
public @interface Generated {

  String[] value();

  String date() default "";

  String comments() default "";
}
