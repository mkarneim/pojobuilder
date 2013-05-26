package net.karneim.pojobuilder;

import net.karneim.pojobuilder.model.BuilderM;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testdata.factory.Contact;
import testdata.factory.PojoFactory;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import java.util.List;

import static net.karneim.pojobuilder.matchers.PBMatchers.*;
import static org.junit.Assert.*;

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

    @Test
    public void testFactoryMethodParameterPositionsWithImplicitParameterNames( ) {
        testFactoryMethodParameterPositions( "createContactImplicit" );
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
		assertThat(builder.getProperties(), containsOnly(
                propertyM(named("firstname"), withPosition(0)),
                propertyM(named("surname"), withPosition(1)),
                propertyM(named("email"))
        ));
	}

}
