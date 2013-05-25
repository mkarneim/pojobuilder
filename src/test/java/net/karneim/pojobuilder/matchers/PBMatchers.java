package net.karneim.pojobuilder.matchers;

import net.karneim.pojobuilder.model.PropertyM;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

import javax.lang.model.element.Element;
import java.util.Collection;
import java.util.List;

/**
 * Hamcrest matchers for the Pojobuilder internal model
 */
public class PBMatchers {
    public static <T> Matcher<Collection<T>> contains(final T element) {
        Matcher<Collection<T>> result = new TypeSafeMatcher<Collection<T>>() {
            @Override
            public boolean matchesSafely(Collection<T> actual) {
                return actual.contains(element);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("contains element ");
                description.appendValue(element);
            }

        };
        return result;
    }

    public static Matcher<Collection<PropertyM>> containsPropertyWithName(final String aName) {
        Matcher<Collection<PropertyM>> result = new TypeSafeMatcher<Collection<PropertyM>>() {
            @Override
            public boolean matchesSafely(Collection<PropertyM> actual) {
                for (PropertyM p : actual) {
                    if (aName.equals(p.getName().toString())) {
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

    public static Matcher<Collection<PropertyM>> doesNotContainPropertyWithName(final String aName) {
        Matcher<Collection<PropertyM>> result = new TypeSafeMatcher<Collection<PropertyM>>() {
            @Override
            public boolean matchesSafely(Collection<PropertyM> actual) {
                for (PropertyM p : actual) {
                    if (aName.equals(p.getName().toString())) {
                        return false;
                    }
                }
                return true;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("name ");
                description.appendValue(aName);
            }

        };
        return result;
    }

    public static Matcher<List<? extends Element>> containsElementWithName(final String aName) {
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
