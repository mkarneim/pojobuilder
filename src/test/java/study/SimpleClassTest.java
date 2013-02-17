package study;

import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import study.buildermodelproducer.TestBase;
import testenv.ProcessingEnvironmentRunner;


@RunWith(ProcessingEnvironmentRunner.class)
public class SimpleClassTest extends TestBase {
	public static class SampleClass {
		@SuppressWarnings("unused")
		private String name;
	}

	private Elements underTest;

	@Before
	public void setupEnv() {
		ProcessingEnvironment env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		underTest = env.getElementUtils();
	}


	@Test
	public void testGetTypeElementShouldReturnClass() {
		// Given:
		Class<?> aClass = SampleClass.class;
		
		// When:
		TypeElement el = underTest.getTypeElement(aClass.getCanonicalName());

		// Then:
		assertEquals("kind", ElementKind.CLASS, el.getKind());
		assertEquals("simpleName",  aClass.getSimpleName(), el.getSimpleName().toString());
	}

	@Test
	public void testGetAllMembersShouldReturnAllAccesibleFields() {
		// Given:
		Class<?> aClass = SampleClass.class;
		TypeElement el = underTest.getTypeElement(aClass.getCanonicalName());

		// When:
		//   note: getAllMembers returns the members as they are accessible from inside the given class
		List<? extends Element> members = underTest.getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);

		// Then:
		assertEquals("size", 1, fields.size());
	}

	@Test
	public void testGetSimpleNameShouldReturnNameOfField() {
		// Given:
		Class<?> aClass = SampleClass.class;
		TypeElement el = underTest.getTypeElement(aClass.getCanonicalName());
		List<? extends Element> members = underTest.getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);
		VariableElement field = fields.get(0);
		
		// When:
		Name name = field.getSimpleName();

		// Then:
		assertTrue(name.toString().equals("name"));
	}

	@Test
	public void testAsTypeShouldReturnDeclaredType() {
		// Given:
		Class<?> aClass = SampleClass.class;
		TypeElement el = underTest.getTypeElement(aClass.getCanonicalName());
		List<? extends Element> members = underTest.getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);
		VariableElement field = fields.get(0);
		
		// When:
		TypeMirror fieldType = field.asType();

		// Then:
		assertTrue(fieldType.getKind() == TypeKind.DECLARED);
		DeclaredType decType = (DeclaredType) fieldType;
		assertTrue(decType.asElement().getSimpleName().toString().equals(String.class.getSimpleName()));
	}
}
