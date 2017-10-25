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
  public final String DEFAULT_SETTER_NAME = "with*";
  public final String DEFAULT_FACTORY_METHOD = "";
  public final String DEFAULT_INCLUSION_PATTERN = "*";

  /**
   * Specifies the base class of the generated builder.
   *
   * @return the base class of the generated builder
   */
  Class<?> withBaseclass() default Object.class;

  /**
   * Specifies the generic builder interface of the generated builder. This interface must declare exactly one type
   * parameter and a <code>build()</code> (or alternatively a <code>get()</code>) method having this type as return
   * type.
   * <p>
   * For example:
   *
   * <pre>
   * <code>
   * public interface Builder{@literal <}T{@literal >} {
   *   T build();
   * }
   * </code> </pre>
   *
   * @return the generic interface of the generated builder or {@link Void}, if no interface is specified
   */
  Class<?> withBuilderInterface() default Void.class;

  /**
   * Specifies whether the generated builder should define builder-based setter-methods using the builder interface.
   * <p>
   * When set to <code>true</code>, the {@link GeneratePojoBuilder#withBuilderInterface()} must specify a valid
   * interface.
   *
   * @return whether the generated builder should define builder-based setter-methods
   */
  boolean withBuilderProperties() default false;

  /**
   * Specifies whether the generated builder should define optional-based setter-methods using the specified 'Optional'
   * type.
   * <p>
   * The 'Optional' type can have any name but must be interface-compatible with the following interface:
   *
   * <pre>
   * <code>
   * public interface Optional{@literal <}T{@literal >} {
   *   T get();
   *   boolean isPresent();
   * }
   * </code> </pre>
   *
   * where T is the generic type parameter matching the property's type.
   * <p>
   * Examples are Google Guava's com.google.common.base.Optional and java.util.Optional introduced with Java 8.
   *
   * @return the 'Optional' type used for generating the optional-based setter-methods, or {@link Void} if no
   *         optional-based setter-methods should be generated
   */
  Class<?> withOptionalProperties() default Void.class;

  /**
   * Specifies the name of the generated builder. An asterisk will be replaced with the pojos simple name. Default is
   * "*Builder".
   *
   * @return the name of the generated builder
   */
  String withName() default DEFAULT_NAME;

  /**
   * Specifies the name pattern of the generated setter-methods. An asterisk will be replaced with the property's
   * original name. Default is "with*".
   *
   * @return the name pattern of the generated setter-methods.
   */
  String withSetterNamePattern() default DEFAULT_SETTER_NAME;

  /**
   * Specifies the package of the generated builder. An asterisk will be replaced with the pojos package. Default is
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
   * Specifies the name of a static factory method added to the builder. An asterisk will be replaced by the pojos
   * simple name. Default is "" meaning not to generate this method.
   *
   * <pre>
   * <code>
   * public class MyPojoBuilder {
   *   public static MyPojoBuilder myPojo() {
   *     return new MyPojoBuilder();
   *   }
   * }
   * </code> </pre>
   *
   * @return the factory-method name
   */
  String withFactoryMethod() default DEFAULT_FACTORY_METHOD;

  /**
   * Specifies the visibility of the builder's constructor.
   *
   * @return the constructor's visibility
   */
  Visibility withConstructor() default Visibility.PUBLIC;

  /**
   * Specifies the validator class that should be used to validate the created pojo. The class must define a
   * <code>validate</code> method having one parameter that is compatible with the pojo's type. If the validation fails,
   * the method must throw some runtime exception (or one of its subclasses).
   * <p>
   * This is an example of how a validator could look like:
   *
   * <pre>
   * <code>
   * public class MyPojoValidator {
   *   public void validate(Pojo pojo) {
   *     if ( - check if pojo is invalid -) {
   *       throw new RuntimeException(&quot;This pojo is invalid!&quot;);
   *     }
   *   }
   * }
   * </code> </pre>
   *
   * @return the validator's class, or {@link Void}, if no validator should be used.
   */
  Class<?> withValidator() default Void.class;

  /**
   * Specifies which of the pojo's properties will be included into the generated builder. All properties that match any
   * property pattern in the specified array will be included. All other non-mandatory properties will be excluded.
   * Mandatory properties are those which are passed as constructor or factory method arguments. They will never be
   * excluded, neither explicitly nor implicitly.
   * <p>
   * The property pattern consists of a name pattern followed by an optional type pattern.
   * <p>
   * The syntax is <code>&lt;name pattern&gt;[:&lt;type pattern&gt;].</code> The pattern supports the asterisk '*'
   * wildcard character that matches any character.
   * <p>
   * Default is '*'.
   *
   * @return the inclusion patterns
   */
  String[] includeProperties() default {DEFAULT_INCLUSION_PATTERN};

  /**
   * Specifies which of the pojo's properties will be excluded from the generated builder. All property that match any
   * property pattern in the specified array will be excluded, except those that are mandatory. Mandatory properties are
   * those which are passed as constructor or factory method arguments. They will never be excluded, neither explicitly
   * nor implicitly.
   * <p>
   * The property pattern consists of a name pattern followed by an optional type pattern.
   * <p>
   * The syntax is <code>&lt;name pattern&gt;[:&lt;type pattern&gt;].</code> The pattern supports the asterisk '*'
   * wildcard character that matches any character.
   * <p>
   * Default is the empty array.
   *
   * @return the exclusion patterns
   */
  String[] excludeProperties() default {};
}
