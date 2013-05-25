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
import javax.lang.model.util.Elements;

import net.karneim.pojobuilder.TestBase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testenv.ProcessingEnvironmentRunner;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(ProcessingEnvironmentRunner.class)
public class ClassWithGenericPropertyTest extends TestBase {
	public static class SampleClass {
		@SuppressWarnings("unused")
		private List<String> names;
	}

	private Elements underTest;

	@Before
	public void setupEnv() {
		ProcessingEnvironment env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		underTest = env.getElementUtils();
	}

	@Test
	public void testAsTypeOnVariableElementShouldReturnCorrectTypeMirror() {
		// Given:
		Class<?> aClass = SampleClass.class;

		// When:
		TypeElement el = underTest.getTypeElement(aClass.getCanonicalName());
		List<? extends Element> members = underTest.getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);
		VariableElement field = fields.get(0);
		TypeMirror fieldType = field.asType();

		// Then:
		assertTrue(fieldType.getKind() == TypeKind.DECLARED);
		DeclaredType decType = (DeclaredType) fieldType;
		assertTrue(decType.asElement().getSimpleName().toString().equals(List.class.getSimpleName()));
	}

	@Test
	public void testGetTypeArgumentsOnDeclaredTypeShouldReturnCorrectListOfTypeMirrors() {
		// Given:
		Class<?> aClass = SampleClass.class;

		// When:
		TypeElement el = underTest.getTypeElement(aClass.getCanonicalName());
		List<? extends Element> members = underTest.getAllMembers(el);
		List<VariableElement> fields = ElementFilter.fieldsIn(members);
		VariableElement field = fields.get(0);
		TypeMirror fieldType = field.asType();
		DeclaredType declaredFieldType = (DeclaredType) fieldType;
		List<? extends TypeMirror> paramTypes = declaredFieldType.getTypeArguments();

		// Then:
		assertTrue(paramTypes.size() == 1);
		TypeMirror param0Type = paramTypes.get(0);
		assertEquals("kind", TypeKind.DECLARED, param0Type.getKind());
		DeclaredType declaredParam0Type = (DeclaredType) param0Type;
		assertEquals("simpleName", String.class.getSimpleName(), declaredParam0Type.asElement().getSimpleName()
				.toString());
	}
}
