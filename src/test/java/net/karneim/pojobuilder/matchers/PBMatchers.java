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

    public static Matcher<PropertyM> named( String name ) {
        return hasProperty("name", equalTo(name));
    }

    public static Matcher<PropertyM> withPosition( int position ) {
        return hasProperty( "parameterPos", equalTo( position ));
    }

    public static Matcher<PropertyM> withSetter( String setter ) {
        return hasProperty( "setter", equalTo( setter ));
    }


    public static Matcher<PropertyM> withType( final String qualifiedName ) {
        Matcher<PropertyM> result = new FeatureMatcher<PropertyM, String>(equalTo(qualifiedName),"type", "type" ) {
            @Override
            protected String featureValueOf(PropertyM propertyM) {
                return propertyM.getType().getQualifiedName();
            }
        };
        return result;
    }

    public static Matcher<PropertyM> propertyM(Matcher<PropertyM>... features) {
        return allOf(features);
    }

    public static Matcher<Iterable<? extends PropertyM>> containsOnly(Matcher<PropertyM>... matchers) {
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
