package net.karneim.pojobuilder;

import static net.karneim.pojobuilder.matchers.PBMatchers.named;
import static net.karneim.pojobuilder.matchers.PBMatchers.propertyM;
import static net.karneim.pojobuilder.matchers.PBMatchers.withSetter;
import static net.karneim.pojobuilder.matchers.PBMatchers.withType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.PropertyM;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testdata.array.Order;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree(TestBase.SRC_TESTDATA_DIR)
public class ArraysTest extends TestBase {

	private ProcessingEnvironment env;

	private GeneratePojoBuilderProcessor underTest;

	@Before
	public void setup() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
        underTest = new GeneratePojoBuilderProcessor(env);
	}

	@Test
	public void testBuilderClassname() {
		// Given:
        String pojoClassname = Order.class.getCanonicalName();
		TypeElement pojoType = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
        Output output = underTest.testProcess(pojoType);
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("builder classname", "OrderBuilder", builder.getType().getSimpleName());
	}

	@Test
	public void testNumberOfProperties() {
		// Given:
		String pojoClassname = Order.class.getName();
		TypeElement pojoType = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
        Output output = underTest.testProcess(pojoType);
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("number of properties", 2, builder.getProperties().size());
	}

    @SuppressWarnings("unchecked")
	@Test
	public void testItemsProperty() {
		// Given:
        String pojoClassname = Order.class.getCanonicalName();
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
        Output output = underTest.testProcess(pojoTypeElement);
		BuilderM builder = output.getBuilder();

		// Then:
        assertThat(builder.getProperties(), Matchers.<PropertyM>hasItem(propertyM(named("items"),
                withType("testdata.array.Item[]"), withSetter("setItems"))));

	}
}
