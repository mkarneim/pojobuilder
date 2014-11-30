package net.karneim.pojobuilder.testenv;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
public @interface AddToSourceTree {
  String[] value();
}
