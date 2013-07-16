package net.karneim.pojobuilder;

import net.karneim.pojobuilder.model.BuilderM;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testdata.factory.Note;
import testdata.factory.PojoFactory;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class FactoryMethodWithNoParametersTest extends TestBase {

    private static String FACTORY_CLASSNAME = PojoFactory.class.getCanonicalName();
    private static String NOTE_CLASSNAME = Note.class.getCanonicalName();

	private Elements elements;

	private GeneratePojoBuilderProcessor underTest;

	@Before
	public void setup() {
		ProcessingEnvironment env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		elements = env.getElementUtils();
        underTest = new GeneratePojoBuilderProcessor(env);
	}

	@Test
	public void testFactoryMethod() {
		// Given:
		TypeElement factoryTypeElement = elements.getTypeElement(FACTORY_CLASSNAME);
		List<ExecutableElement> methods = ElementFilter.methodsIn(elements.getAllMembers(factoryTypeElement));
		ExecutableElement factoryMethod = getFirstMethodByName("createNote", methods);

		// When:
        Output output = underTest.testProcess(factoryMethod);
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("builder classname", "NoteBuilder", builder.getType().getSimpleName());
		assertNotNull("factory", builder.getFactory());
		assertEquals("factory method name", "createNote", builder.getFactory().getMethodName());
	}

}
