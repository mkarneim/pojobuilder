package testdata.constructor;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import java.beans.ConstructorProperties;

/**
 * Test data for constructor selection using class-level GeneratePojoBuilder annotation
 */
public class ClassLevelAnnotation {

    /**
     * Single default constructor
     */
    @GeneratePojoBuilder
    public static class Apple {

        public Apple() {}
    }

    /**
     * Single annotated-constructor with renaming
     */
    @GeneratePojoBuilder
    public static class Banana {
        public String colour;

        @ConstructorProperties("colour")
        public Banana(String color) {
            this.colour = color;
        }
    }

    /**
     * Default constructor where other choices exists
     */
    @GeneratePojoBuilder
    public static class Cherry {
        public String colour;

        @ConstructorProperties({})
        public Cherry() {}

        public Cherry(String colour) {
            this.colour = colour;
        }

    }

    /**
     * Non-default constructor where other choices exists
     */
    @GeneratePojoBuilder
    public static class Dewberry {
        public String colour;

        public Dewberry() {}

        @ConstructorProperties({"colour"})
        public Dewberry(String colour) {
            this.colour = colour;
        }

        public Dewberry(String colour, String other) {
            this(colour);
        }

    }

}
