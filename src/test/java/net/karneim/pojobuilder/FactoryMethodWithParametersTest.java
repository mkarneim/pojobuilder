package net.karneim.pojobuilder;

import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.PropertyM;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testdata.factory.Contact;
import testdata.factory.PojoFactory;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class FactoryMethodWithParametersTest extends TestBase {

	private static String FACTORY_CLASSNAME = PojoFactory.class.getName();
	private static String CONTACT_CLASSNAME = Contact.class.getName();

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
    public void testFactoryMethodParameterPositionsWithParameterNames( ) {
        testFactoryMethodParameterPositions( "createContact" );
    }

    @Test
    public void testFactoryMethodParameterPositionsWithFactoryProperties( ) {
        testFactoryMethodParameterPositions( "createContact2" );
    }

    @Test(expected = BuildException.class)
    public void testFactoryMethodParameterPositionsWithBothAnnotations( ) {
        testFactoryMethodParameterPositions( "createContact3" );
    }

	private void testFactoryMethodParameterPositions( String factoryMethodName ) {
		// Given:
		TypeElement factoryTypeElement = elements.getTypeElement(FACTORY_CLASSNAME);
		List<ExecutableElement> methods = ElementFilter.methodsIn(elements.getAllMembers(factoryTypeElement));
		ExecutableElement factoryMetod = getFirstMethodByName(factoryMethodName, methods);
		TypeElement pojoTypeElement = elements.getTypeElement(CONTACT_CLASSNAME);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement, factoryMetod));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("builder classname", "ContactBuilder", builder.getType().getSimpleName());
		assertNotNull("factory", builder.getFactory());
		assertEquals("factory method name", factoryMethodName, builder.getFactory().getMethodName());
		assertThat(builder.getProperties(), containsPropertyWithName("surname"));
		assertThat(builder.getProperties(), containsPropertyWithName("firstname"));
		assertThat(builder.getProperties(), containsPropertyWithName("email"));
		PropertyM p0 = getFirstPropertyByName(builder.getProperties(), "firstname");
		assertEquals("parameter position", 0, (int) p0.getParameterPos());
		PropertyM p1 = getFirstPropertyByName(builder.getProperties(), "surname");
		assertEquals("parameter position", 1, (int) p1.getParameterPos());
	}

}
