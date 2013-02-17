package study;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import study.buildermodelproducer.TestBase;
import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
public class ElementVisitorTest extends TestBase {
	public static class SampleClass<T> {
		@SuppressWarnings("unused")
		private String fieldA;
		public String fieldB;
		public T fieldC;

		public SampleClass() {
		}

		public void foo() {

		}
	}

	public static class SubClass extends SampleClass<Integer> {
	}

	private Elements elements;
	private CountingElementVisitor underTest = new CountingElementVisitor();

	@Before
	public void setupEnv() {
		ProcessingEnvironment env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		elements = env.getElementUtils();
	}

	@Test
	public void testScanOnVisitorShouldVisitAllDeclaredFieldsForGivenSampleClass() {
		// Given:
		TypeElement sampleClass = elements.getTypeElement(SampleClass.class.getCanonicalName());

		// When:
		underTest.scan(sampleClass);

		// Then:
		assertEquals("variableCount", 3, underTest.getVariableCount());
		assertThat(ElementFilter.fieldsIn(underTest.getVisited()), containsElementWithName("fieldA"));
		assertThat(ElementFilter.fieldsIn(underTest.getVisited()), containsElementWithName("fieldB"));
		assertThat(ElementFilter.fieldsIn(underTest.getVisited()), containsElementWithName("fieldC"));
	}

	@Test
	public void testScanOnVisitorShouldVisitAllExecutableMembersForGivenSampleClass() {
		// Given:
		TypeElement sampleClass = elements.getTypeElement(SampleClass.class.getCanonicalName());

		// When:
		underTest.scan(sampleClass);

		// Then:
		// expecting 2 executables: 1 constructor and 1 method
		assertEquals("executableCount", 2, underTest.getExecutableCount());
		assertThat(ElementFilter.constructorsIn(underTest.getVisited()), containsElementWithName("<init>"));
		assertThat(ElementFilter.methodsIn(underTest.getVisited()), containsElementWithName("foo"));
	}

	@Test
	public void testScanOnVisitorMustNotVisitInheritedFieldsForGivenSubClass() {
		// Given:
		TypeElement subClass = elements.getTypeElement(SubClass.class.getCanonicalName());

		// When:
		underTest.scan(subClass);

		// Then:
		assertEquals("variableCount", 0, underTest.getVariableCount());
	}

	@Test
	public void testScanOnVisitorMustNotVisitInheritedExecutableMembersForGivenSubClass() {
		// Given:
		TypeElement subClass = elements.getTypeElement(SubClass.class.getCanonicalName());

		// When:
		underTest.scan(subClass);

		// Then:
		// we expect to find only the default constructor
		assertEquals("variableCount", 1, underTest.getExecutableCount());
		assertThat(ElementFilter.constructorsIn(underTest.getVisited()), containsElementWithName("<init>"));
	}

}
