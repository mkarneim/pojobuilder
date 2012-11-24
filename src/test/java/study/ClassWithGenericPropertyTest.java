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
public class ClassWithGenericPropertyTest {
	public static class SampleClass {
		private List<String> names;
	}

	ProcessingEnvironment env = ProcessingEnvironmentRunner.getProcessingEnvironment();

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
		Assert.assertTrue(field.getSimpleName().toString().equals("names"));
	}

	@Test
	public void testCanGetTypeOfField() {
		// Given:
		TypeElement el = env.getElementUtils().getTypeElement(SampleClass.class.getCanonicalName());

		// When:
		List<? extends Element> members = env.getElementUtils().getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);
		VariableElement field = fields.get(0);
		TypeMirror fieldType = field.asType();

		// Then:
		Assert.assertTrue(fieldType.getKind() == TypeKind.DECLARED);
		DeclaredType decType = (DeclaredType) fieldType;
		Assert.assertTrue(decType.asElement().getSimpleName().toString().equals(List.class.getSimpleName()));
	}
	
	@Test
	public void testCanGetParameterTypeOfField() {
		// Given:
		TypeElement el = env.getElementUtils().getTypeElement(SampleClass.class.getCanonicalName());

		// When:
		List<? extends Element> members = env.getElementUtils().getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);
		VariableElement field = fields.get(0);
		TypeMirror fieldType = field.asType();
		DeclaredType declaredFieldType = (DeclaredType) fieldType;
		List<? extends TypeMirror> paramTypes = declaredFieldType.getTypeArguments();
		
		// Then:
		//TypeMirror  env.getTypeUtils().asMemberOf(classDeclaredType, field);
		Assert.assertTrue(paramTypes.size()==1);
		TypeMirror param0Type = paramTypes.get(0);
		Assert.assertEquals("kind", TypeKind.DECLARED, param0Type.getKind());
		DeclaredType declaredParam0Type = (DeclaredType)param0Type;
		Assert.assertEquals("simpleName", String.class.getSimpleName(), declaredParam0Type.asElement().getSimpleName().toString());
	}
}
