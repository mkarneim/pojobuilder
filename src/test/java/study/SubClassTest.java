package study;

import static testenv.TestHelpers.containsElementWithName;

import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
public class SubClassTest extends Assert {
	public static class SuperClass {
		@SuppressWarnings("unused")
		private String fieldA;
		public String fieldB;
	}

	public static class SampleClass extends SuperClass {
		@SuppressWarnings("unused")
		private String fieldC;
	}

	private Elements underTest;

	@Before
	public void setupEnv() {
		ProcessingEnvironment env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		underTest = env.getElementUtils();
	}

	@Test
	public void testGetAllMembersOnElementsShouldReturnNoPrivateSuperMembers() {
		// Given:
		Class<?> aClass = SampleClass.class;

		// When:
		// getAllMembers returns the members as they are visible from
		// inside the given class
		TypeElement el = underTest.getTypeElement(aClass.getCanonicalName());
		List<? extends Element> members = underTest.getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);

		// Then:
		assertEquals("size", 2, fields.size());
		assertThat(fields, containsElementWithName("fieldB"));
		assertThat(fields, containsElementWithName("fieldC"));
	}

}
