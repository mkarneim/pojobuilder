package study;

import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import testenv.ProcessingEnvironmentRunner;


@RunWith(ProcessingEnvironmentRunner.class)
public class SimpleClassTest {
	public static class SampleClass {
		@SuppressWarnings("unused")
		private String name;
	}

	ProcessingEnvironment env = ProcessingEnvironmentRunner.getProcessingEnvironment();

	@Test
	public void testEnvIsAvailable() {
		// Given:
		
		// When:

		// Then:
		Assert.assertNotNull(env);
	}
	
	@Test
	public void testCanGetTypeElement() {
		// Given:

		// When:
		TypeElement el = env.getElementUtils().getTypeElement(SampleClass.class.getCanonicalName());

		// Then:
		Assert.assertTrue(el.getKind() == ElementKind.CLASS);
	}

	@Test
	public void testCanGetFields() {
		// Given:
		TypeElement el = env.getElementUtils().getTypeElement(SampleClass.class.getCanonicalName());

		// When:
		List<? extends Element> members = env.getElementUtils().getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);

		// Then:
		Assert.assertTrue(fields.size() == 1);
	}

	@Test
	public void testCanGetNameOfField() {
		// Given:
		TypeElement el = env.getElementUtils().getTypeElement(SampleClass.class.getCanonicalName());
		
		// When:
		List<? extends Element> members = env.getElementUtils().getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);
		VariableElement field = fields.get(0);

		// Then:
		Assert.assertTrue(field.getSimpleName().toString().equals("name"));
	}

	@Test
	public void testAsTypeFor_Name_FieldReturnsDeclaredType() {
		// Given:
		TypeElement el = env.getElementUtils().getTypeElement(SampleClass.class.getCanonicalName());
		List<? extends Element> members = env.getElementUtils().getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);
		VariableElement field = fields.get(0);
		
		// When:
		TypeMirror fieldType = field.asType();

		// Then:
		Assert.assertTrue(fieldType.getKind() == TypeKind.DECLARED);
		DeclaredType decType = (DeclaredType) fieldType;
		Assert.assertTrue(decType.asElement().getSimpleName().toString().equals(String.class.getSimpleName()));
	}
}
