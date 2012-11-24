package testenv;

import java.util.List;

import javax.lang.model.element.Element;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class Matchers {

	public static Matcher<List<? extends Element>> containsName(final String aName) {
		Matcher<List<? extends Element>> result = new BaseMatcher<List<? extends Element>>() {
	
			@Override
			public boolean matches(Object item) {
				@SuppressWarnings("unchecked")
				List<Element> actual = (List<Element>)item;
				for( Element elem: actual) {
					if ( aName.equals(elem.getSimpleName().toString())) {
						return true;
					}
				}
				return false;
			}
	
			@Override
			public void describeTo(Description description) {
				description.appendText("name ");
				description.appendValue( aName);
			}
			
		};
		return result;
	}

}
