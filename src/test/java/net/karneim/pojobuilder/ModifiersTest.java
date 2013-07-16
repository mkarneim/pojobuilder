package net.karneim.pojobuilder;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.TypeM;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testdata.modifiers.Contact;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import static net.karneim.pojobuilder.matchers.PBMatchers.*;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class ModifiersTest extends TestBase {

	private static final String BUILDER = "ContactBuilder";

	private Elements elements;

	private GeneratePojoBuilderProcessor underTest;

	@Before
	public void setup() {
		ProcessingEnvironment env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		elements = env.getElementUtils();
        underTest = new GeneratePojoBuilderProcessor(env);
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectProductType() {
		// Given:
        String pojoClassname = Contact.class.getCanonicalName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
        Output output = underTest.testProcess(pojoType);
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("productType", TypeM.get(pojoClassname), builder.getProductType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectBuilderType() {
		// Given:
        String pojoClassname = Contact.class.getCanonicalName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
        Output output = underTest.testProcess(pojoType);
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("type", BUILDER, builder.getType().getSimpleName());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectSuperType() {
		// Given:
        String pojoClassname = Contact.class.getCanonicalName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
        Output output = underTest.testProcess(pojoType);
		BuilderM builder = output.getBuilder();

		// Then:
        assertEquals("type", TypeM.get(Object.class.getCanonicalName()), builder.getSuperType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectSelfType() {
		// Given:
        String pojoClassname = Contact.class.getCanonicalName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
        Output output = underTest.testProcess(pojoType);
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("selfType", BUILDER, builder.getSelfType().getSimpleName());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectProperties() {
		// Given:
        String pojoClassname = Contact.class.getCanonicalName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
        Output output = underTest.testProcess(pojoType);
		BuilderM builder = output.getBuilder();

		// Then:
        assertThat(builder.getProperties(), containsOnly(
                propertyM(named("firstname"), withType("java.lang.String")),
                propertyM(named("surname"), withType("java.lang.String")),
                propertyM(named("email"), withType("java.lang.String"))
        ));

        assertEquals("selfType", BUILDER, builder.getSelfType().getSimpleName());

        assertThat("size of properties to set", builder.getPropertiesForSetters(), empty());
        assertThat("size of properties to assign", builder.getPropertiesForAssignment(), hasSize(2));
	}

}
