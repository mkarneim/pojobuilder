package study;

import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
public class ClassWithGenericPropertyTest {
	public static class SampleClass {
		@SuppressWarnings("unused")
		private List<String> names;
	}

	private ProcessingEnvironment env;

	@Before
	public void setupEnv() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
	}

	@Test
	public void testAsTypeOnFieldShouldReturnDeclaredType() {
		// Given:
		Class<?> aClass = SampleClass.class;
		TypeElement el = env.getElementUtils().getTypeElement(aClass.getCanonicalName());
		List<? extends Element> members = env.getElementUtils().getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);
		VariableElement field = fields.get(0);

		// When:
		TypeMirror fieldType = field.asType();

		// Then:
		Assert.assertTrue(fieldType.getKind() == TypeKind.DECLARED);
		DeclaredType decType = (DeclaredType) fieldType;
		Assert.assertTrue(decType.asElement().getSimpleName().toString().equals(List.class.getSimpleName()));
	}

	@Test
	public void testGetTypeArgumentsOnFieldShouldReturnDeclaredType() {
		// Given:
		Class<?> aClass = SampleClass.class;
		TypeElement el = env.getElementUtils().getTypeElement(aClass.getCanonicalName());
		List<? extends Element> members = env.getElementUtils().getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);
		VariableElement field = fields.get(0);
		TypeMirror fieldType = field.asType();
		DeclaredType declaredFieldType = (DeclaredType) fieldType;

		// When:
		List<? extends TypeMirror> paramTypes = declaredFieldType.getTypeArguments();

		// Then:
		Assert.assertTrue(paramTypes.size() == 1);
		TypeMirror param0Type = paramTypes.get(0);
		Assert.assertEquals("kind", TypeKind.DECLARED, param0Type.getKind());
		DeclaredType declaredParam0Type = (DeclaredType) param0Type;
		Assert.assertEquals("simpleName", String.class.getSimpleName(), declaredParam0Type.asElement().getSimpleName()
				.toString());
	}
}
