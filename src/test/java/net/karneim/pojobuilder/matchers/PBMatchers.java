package net.karneim.pojobuilder.matchers;

import net.karneim.pojobuilder.model.PropertyM;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import javax.lang.model.element.Element;

import static org.hamcrest.Matchers.*;

/**
 * Hamcrest matchers for the Pojobuilder internal model
 */
public class PBMatchers {

    public static Matcher<PropertyM> named(final String name) {
        return new FeatureMatcher<PropertyM, String>(equalTo(name), "named", "name") {
            @Override
            protected String featureValueOf(PropertyM actual) {
                return actual.getName();
            }

        };
    }

    public static Matcher<PropertyM> withPosition(final int position) {
        return new FeatureMatcher<PropertyM, Integer>(equalTo(position), "in position", "position") {
            @Override
            protected Integer featureValueOf(PropertyM actual) {
                return actual.getParameterPos();
            }

        };
    }

    public static Matcher<PropertyM> withSetter(final String setter) {
        return new FeatureMatcher<PropertyM, String>(equalTo(setter), "set via", "setter") {
            @Override
            protected String featureValueOf(PropertyM actual) {
                return actual.getSetter();
            }

        };
    }

    public static Matcher<PropertyM> withType(final String qualifiedName) {
        return new FeatureMatcher<PropertyM, String>(equalTo(qualifiedName), "typed", "type") {
            @Override
            protected String featureValueOf(PropertyM actual) {
                return actual.getType().getQualifiedName();
            }
        };
    }

    // TODO Java7 @SafeVarargs
    public static Matcher<PropertyM> propertyM(Matcher<? super PropertyM>... features) {
        return allOf(features);
    }

    // TODO Java7 @SafeVarargs
    public static <T> Matcher<Iterable<? extends T>> containsOnly(Matcher<? super T>... matchers) {
        return containsInAnyOrder(matchers);
    }

    public static Matcher<Element> elementWithName(final String name) {
        return new FeatureMatcher<Element, String>(equalTo(name), "name", "named") {
            @Override
            protected String featureValueOf(Element actual) {
                return actual.getSimpleName().toString();
            }
        };
    }
}
