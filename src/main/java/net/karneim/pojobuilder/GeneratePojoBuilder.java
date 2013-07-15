package net.karneim.pojobuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation for generating a fluent pojo builder.
 *
 * @author karneim
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface GeneratePojoBuilder {
    /**
     * Specifies the base class of the generated builder.
     *
     * @return the base class of the generated builder
     */
    Class<?> withBaseclass() default Object.class;

    /**
     * Specifies the name of the generated builder. Any asterisk will be
     * replaced with the pojo's simple name. Default is "*Builder".
     *
     * @return the name of the generated builder
     */
    String withName() default "*Builder";

    /**
     * Specifies the package of the generated builder. Any asterisk will be
     * replaced with the pojo's package. Default is "*".
     *
     * @return the package of the generated builder
     */
    String intoPackage() default "*";

    /**
     * Specifies whether the generation gap pattern is used. If enabled this
     * will generate two classes (instead of one), of which one contains the
     * generated code. The other class is for handwritten code. To prevent it
     * from being overwritten please move it out of the generated-sources
     * folder. Default is "false".
     */
    boolean withGenerationGap() default false;

    /**
     * Specifies whether a copy method should be generated. The copy method will
     * take an instance of the built class and will copy all its fields into the
     * builder. This allows it to easily change one or more fields of immutable
     * objects.
     */
    boolean withCopyMethod() default false;
}
