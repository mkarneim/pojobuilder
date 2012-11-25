package study;

import static testenv.TestHelpers.containsElementWithName;

import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import study.SimpleClassTest.SampleClass;
import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
public class SubClassTest {
	public static class SuperClass {
		@SuppressWarnings("unused")
		private String fieldA;
		public String fieldB;
	}

	public static class SampleClass extends SuperClass {
		@SuppressWarnings("unused")
		private String fieldC;
	}

	private ProcessingEnvironment env;

	@Before
	public void setupEnv() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
	}
	
	@Test
	public void testGetAllMembersShouldReturnNoPrivateSuperMembers() {
		// Given:
		Class<?> aClass = SampleClass.class;
		TypeElement el = env.getElementUtils().getTypeElement(aClass.getCanonicalName());

		// When:
		// getAllMembers returns the members as they are visible from
		// inside the given class
		List<? extends Element> members = env.getElementUtils().getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);

		// Then:
		Assert.assertEquals("size", 2, fields.size());
		Assert.assertThat(fields, containsElementWithName("fieldB"));
		Assert.assertThat(fields, containsElementWithName("fieldC"));
	}

}
