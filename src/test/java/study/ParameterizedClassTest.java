package study;

import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.ElementFilter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
public class ParameterizedClassTest extends Assert {
	public static class ParentClass<T> {
		public T value;
	}

	public static class SampleClass extends ParentClass<String> {

	}

	private ProcessingEnvironment env;

	@Before
	public void setupEnv() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
	}

	@Test
	public void testGetTypeParametersOnTypeElementShouldReturnExpectedTypeParameterElement() {
		// Given:
		Class<?> aClass = ParentClass.class;

		// When:
		TypeElement el = env.getElementUtils().getTypeElement(aClass.getCanonicalName());
		List<? extends TypeParameterElement> typeParameters = el.getTypeParameters();

		// Then:
		assertEquals("size", 1, typeParameters.size());
		TypeParameterElement tp0 = typeParameters.get(0);
		assertEquals("kind", TypeKind.TYPEVAR, tp0.asType().getKind());
		assertEquals("simpleName", "T", tp0.getSimpleName().toString());
	}

	@Test
	public void testAsTypeOnVariableElementShouldReturnTypeMirrorOfField() {
		// Given:
		Class<?> parentClass = ParentClass.class;

		// When:
		TypeElement el = env.getElementUtils().getTypeElement(parentClass.getCanonicalName());
		List<? extends Element> members = env.getElementUtils().getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);
		VariableElement field = fields.get(0);
		TypeMirror fieldType = field.asType();

		// Then:
		assertEquals("kind", TypeKind.TYPEVAR, fieldType.getKind());
		TypeVariable typeVar = (TypeVariable) fieldType;
		assertEquals("name", "T", typeVar.asElement().getSimpleName().toString());
	}

	@Test
	public void testAsMemberOfOnTypesShouldReturnConcreteTypeMirrorForGenericField() {
		// Given:
		Class<?> aClass = SampleClass.class;

		// When:
		TypeElement el = env.getElementUtils().getTypeElement(aClass.getCanonicalName());
		DeclaredType declType = (DeclaredType) el.asType();
		List<? extends Element> members = env.getElementUtils().getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);
		assertEquals("size", 1, fields.size());
		VariableElement field = fields.get(0);
		TypeMirror actualType = env.getTypeUtils().asMemberOf(declType, field);

		// Then:
		assertEquals("kind", TypeKind.DECLARED, actualType.getKind());
		DeclaredType actualDeclaredType = (DeclaredType) actualType;
		assertEquals("name", String.class.getSimpleName(), actualDeclaredType.asElement().getSimpleName().toString());
	}
}
