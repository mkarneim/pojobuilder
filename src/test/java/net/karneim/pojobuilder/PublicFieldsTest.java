package net.karneim.pojobuilder;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.TypeM;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testdata.publicfields.Item;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import static net.karneim.pojobuilder.matchers.PBMatchers.*;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class PublicFieldsTest extends TestBase {

	private static final String ITEM_BUILDER = "testdata.publicfields.ItemBuilder";

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
        String pojoClassname = Item.class.getCanonicalName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
        Output output = underTest.testProcess(pojoType);
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("productType", TypeM.get(pojoClassname), builder.getPojoType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectBuilderType() {
		// Given:
        String pojoClassname = Item.class.getCanonicalName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
        Output output = underTest.testProcess(pojoType);
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("type", TypeM.get(ITEM_BUILDER), builder.getType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectSuperType() {
		// Given:
        String pojoClassname = Item.class.getCanonicalName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
        Output output = underTest.testProcess(pojoType);
		BuilderM builder = output.getBuilder();

		// Then:
        assertThat(builder.getSuperType(), is(nullValue()));
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectSelfType() {
		// Given:
        String pojoClassname = Item.class.getCanonicalName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
        Output output = underTest.testProcess(pojoType);
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("selfType", TypeM.get(ITEM_BUILDER), builder.getSelfType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectProperties() {
		// Given:
        String pojoClassname = Item.class.getCanonicalName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
        Output output = underTest.testProcess(pojoType);
		BuilderM builder = output.getBuilder();

		// Then:
        assertThat(builder.getProperties(), containsOnly(
                propertyM(named("name"), withType("java.lang.String")),
                propertyM(named("amount"), withType("int"))
        ));

        assertEquals("selfType", TypeM.get(ITEM_BUILDER), builder.getSelfType());

        assertThat("size of properties to set", builder.getPropertiesForSetters(), empty());
        assertThat("size of properties to assign", builder.getPropertiesForAssignment(), hasSize(2));
	}

}
