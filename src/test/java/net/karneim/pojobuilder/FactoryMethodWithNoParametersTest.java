package net.karneim.pojobuilder;

import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;

import net.karneim.pojobuilder.model.BuilderM;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testdata.factory.Note;
import testdata.factory.PojoFactory;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class FactoryMethodWithNoParametersTest extends TestBase {

	private static String FACTORY_CLASSNAME = PojoFactory.class.getName();
	private static String NOTE_CLASSNAME = Note.class.getName();

	private Elements elements;

	private BuilderModelProducer underTest;

	@Before
	public void setup() {
		ProcessingEnvironment env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		elements = env.getElementUtils();
		TypeMUtils typeMUtils = new TypeMUtils();
		underTest = new BuilderModelProducer(env, typeMUtils);
	}

	@Test
	public void testFactoryMethod() {
		// Given:
		TypeElement factoryTypeElement = elements.getTypeElement(FACTORY_CLASSNAME);
		List<ExecutableElement> methods = ElementFilter.methodsIn(elements.getAllMembers(factoryTypeElement));
		ExecutableElement factoryMetod = getFirstMethodByName("createNote", methods);
		TypeElement pojoTypeElement = elements.getTypeElement(NOTE_CLASSNAME);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement, factoryMetod));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("builder classname", "NoteBuilder", builder.getType().getSimpleName());
		assertNotNull("factory", builder.getFactory());
		assertEquals("factory method name", "createNote", builder.getFactory().getMethodName());
	}

}
