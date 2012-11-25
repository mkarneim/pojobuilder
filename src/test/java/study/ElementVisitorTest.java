package study;

import static testenv.TestHelpers.containsElementWithName;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testenv.ElementVisitorMock;
import testenv.ProcessingEnvironmentRunner;


@RunWith(ProcessingEnvironmentRunner.class)
public class ElementVisitorTest {
	public static class ParentClass<T> {
		@SuppressWarnings("unused")
		private String fieldA;
		public String fieldB;
		public T fieldC;

		public ParentClass() {
		}
		
		public void foo() {
			
		}
	}

	public static class SubClass extends ParentClass<Integer> {
	}

	private ProcessingEnvironment env;

	@Before
	public void setupEnv() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
	}


	ElementVisitorMock underTest = new ElementVisitorMock();

	@Test
	public void testScanOnParentClassShouldVisitAllDeclaredFields() {
		// Given:
		TypeElement parentClass = env.getElementUtils().getTypeElement(ParentClass.class.getCanonicalName());

		// When:
		underTest.scan(parentClass);

		// Then:
		Assert.assertEquals("variableCount", 3, underTest.getVariableCount());
		Assert.assertThat(ElementFilter.fieldsIn(underTest.getVisited()), containsElementWithName("fieldA"));
		Assert.assertThat(ElementFilter.fieldsIn(underTest.getVisited()), containsElementWithName("fieldB"));
		Assert.assertThat(ElementFilter.fieldsIn(underTest.getVisited()), containsElementWithName("fieldC"));
	}

	@Test
	public void testScanOnParentClassShouldVisitAllExectableMembers() {
		// Given:
		TypeElement parentClass = env.getElementUtils().getTypeElement(ParentClass.class.getCanonicalName());

		// When:
		underTest.scan(parentClass);

		// Then:
		// expecting 2 executables: 1 constructor and 1 method
		Assert.assertEquals("executableCount", 2, underTest.getExecutableCount());
		Assert.assertThat(ElementFilter.constructorsIn(underTest.getVisited()), containsElementWithName("<init>"));
		Assert.assertThat(ElementFilter.methodsIn(underTest.getVisited()), containsElementWithName("foo"));
	}

	@Test
	public void testScanOnSubClassMustNotVisitFieldsInParentClass() {
		// Given:
		TypeElement subClass = env.getElementUtils().getTypeElement(SubClass.class.getCanonicalName());

		// When:
		underTest.scan(subClass);

		// Then:
		Assert.assertEquals("variableCount", 0, underTest.getVariableCount());
	}
	
	@Test
	public void testScanOnSubClassMustNotVisitExecutableMembersInParentClass() {
		// Given:
		TypeElement subClass = env.getElementUtils().getTypeElement(SubClass.class.getCanonicalName());

		// When:
		underTest.scan(subClass);

		// Then:
		//   we expect to find only the default constructor
		Assert.assertEquals("variableCount", 1, underTest.getExecutableCount()); 
		Assert.assertThat(ElementFilter.constructorsIn(underTest.getVisited()), containsElementWithName("<init>")); 
	}

}
