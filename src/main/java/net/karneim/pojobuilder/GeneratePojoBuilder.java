package net.karneim.pojobuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation to trigger the code generation of a fluent pojo builder.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
public @interface GeneratePojoBuilder {
  public final String DEFAULT_NAME = "*Builder";
  public final String DEFAULT_PACKAGE = "*";

  /**
   * Specifies the base class of the generated builder.
   * 
   * @return the base class of the generated builder
   */
  Class<?> withBaseclass() default Object.class;

  /**
   * Specifies the generic builder interface of the generated builder. The interface must declare exactly one type
   * parameter and a build method with this type as return type.
   * <p>
   * For example:
   * 
   * <pre>
   *   public interface Builder&lt;T&gt; {
   *     T build(); 
   *   }
   * </pre>
   * 
   * @return the generic interface of the generated builder
   */
  Class<?> withBuilderInterface() default Void.class;

  /**
   * Specifies whether the generated builder should define builder-based with-methods using the builder interface.
   * <p>
   * When set to <code>true</code>, the {@link GeneratePojoBuilder#withBuilderInterface()} must specify a valid
   * interface.
   * 
   * @return whether the generated builder should define builder-based with-methods
   */
  boolean withBuilderProperties() default false;

  /**
   * Specifies the name of the generated builder. Any asterisk will be replaced with the pojos simple name. Default is
   * "*Builder".
   * 
   * @return the name of the generated builder
   */
  String withName() default DEFAULT_NAME;

  /**
   * Specifies the package of the generated builder. Any asterisk will be replaced with the pojos package. Default is
   * "*".
   * 
   * @return the package of the generated builder
   */
  String intoPackage() default DEFAULT_PACKAGE;

  /**
   * Specifies whether the generation gap pattern is used. If enabled this will generate two classes (instead of one),
   * of which one contains the generated code. The other class is for handwritten code. To prevent it from being
   * overwritten please move it out of the generated-sources folder. Default is "false".
   * 
   * @return <code>true</code> if the generation gap should be used
   */
  boolean withGenerationGap() default false;

  /**
   * Specifies whether a copy method should be generated. The copy method will take an instance of the built class and
   * will copy all its fields into the builder. This allows it to easily change one or more fields of immutable objects.
   * 
   * @return <code>true</code> if a copy method should be generated
   */
  boolean withCopyMethod() default false;
  
  /**
   * Specifies a validator method implemented in base class. (withBaseclass must be set)
   * 
   * @return the validator method name
   */
  String withValidatorMethod() default "";
}
