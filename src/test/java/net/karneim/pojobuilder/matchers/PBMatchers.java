package net.karneim.pojobuilder.matchers;

import net.karneim.pojobuilder.model.PropertyM;
import org.hamcrest.Description;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import javax.lang.model.element.Element;
import java.util.List;

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

    public static Matcher<PropertyM> propertyM(Matcher<? super PropertyM>... features) {
        return allOf(features);
    }

    public static Matcher<Iterable<? extends PropertyM>> containsOnly(Matcher<? super PropertyM>... matchers) {
        return containsInAnyOrder(matchers);
    }

    public static Matcher<List<? extends Element>> containsElementWithName(final String aName) {
//        Matcher<Element> p = hasProperty("simpleName", hasToString(equalTo(aName)));
//        return hasItem(p);
        Matcher<List<? extends Element>> result = new TypeSafeMatcher<List<? extends Element>>() {
            @Override
            public boolean matchesSafely(List<? extends Element> actual) {
                for (Element elem : actual) {
                    if (aName.equals(elem.getSimpleName().toString())) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("name ");
                description.appendValue(aName);
            }

        };
        return result;
    }
}
