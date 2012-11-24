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
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.ElementFilter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import testenv.ProcessingEnvironmentRunner;


@RunWith(ProcessingEnvironmentRunner.class)
public class ParameterizedClassTest {
	public static class ParentClass<T> {
		public T value;
	}

	public static class SubClass extends ParentClass<String> {

	}

	ProcessingEnvironment env = ProcessingEnvironmentRunner.getProcessingEnvironment();

	@Test
	public void testCanGetTypeElement() {
		// Given:

		// When:
		TypeElement el = env.getElementUtils().getTypeElement(ParentClass.class.getCanonicalName());

		// Then:
		Assert.assertTrue(el.getKind() == ElementKind.CLASS);
	}

	@Test
	public void testCanGetFields() {
		// Given:
		TypeElement el = env.getElementUtils().getTypeElement(ParentClass.class.getCanonicalName());
		List<? extends Element> members = env.getElementUtils().getAllMembers(el);
		
		// When:
		List<VariableElement> fields = ElementFilter.fieldsIn(members);

		// Then:
		Assert.assertEquals("size", 1, fields.size());
	}

	@Test
	public void testCanGetNameOfField() {
		// Given:
		TypeElement el = env.getElementUtils().getTypeElement(ParentClass.class.getCanonicalName());
		List<? extends Element> members = env.getElementUtils().getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);
		
		// When:
		VariableElement field = fields.get(0);

		// Then:
		Assert.assertEquals("name", "value", field.getSimpleName().toString());
	}

	@Test
	public void testCanGetTypeOfField() {
		// Given:
		TypeElement el = env.getElementUtils().getTypeElement(ParentClass.class.getCanonicalName());
		List<? extends Element> members = env.getElementUtils().getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);
		VariableElement field = fields.get(0);
		
		// When:
		TypeMirror fieldType = field.asType();

		// Then:
		Assert.assertEquals("kind", TypeKind.TYPEVAR, fieldType.getKind());
		TypeVariable typeVar = (TypeVariable) fieldType;
		Assert.assertEquals("name", "T", typeVar.asElement().getSimpleName().toString());
	}

	@Test
	public void testAsMemberOfReturnsConcreteTypeOfFieldInSubclass() {
		// Given:
		TypeElement el = env.getElementUtils().getTypeElement(SubClass.class.getCanonicalName());
		List<? extends Element> members = env.getElementUtils().getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);
		Assert.assertEquals("size", 1, fields.size());
		VariableElement field = fields.get(0);

		// When:
		TypeMirror actualType = env.getTypeUtils().asMemberOf((DeclaredType)el.asType(), field);

		// Then:
		Assert.assertEquals("kind", TypeKind.DECLARED, actualType.getKind());
		DeclaredType actualDeclaredType = (DeclaredType)actualType;
		Assert.assertEquals("name", String.class.getSimpleName(), actualDeclaredType.asElement().getSimpleName().toString());
	}
}
