package net.karneim.pojobuilder;

import net.karneim.pojobuilder.model.BuilderM;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testdata.array.Order;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import static net.karneim.pojobuilder.matchers.PBMatchers.*;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree(TestBase.SRC_TESTDATA_DIR)
public class ArraysTest extends TestBase {

	private ProcessingEnvironment env;

	private BuilderModelProducer underTest;

	@Before
	public void setup() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		TypeMUtils typeMUtils = new TypeMUtils();
		underTest = new BuilderModelProducer(env, typeMUtils);
	}

	@Test
	public void testBuilderClassname() {
		// Given:
		String pojoClassname = Order.class.getName();
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("builder classname", "OrderBuilder", builder.getType().getSimpleName());
	}

	@Test
	public void testNumberOfProperties() {
		// Given:
		String pojoClassname = Order.class.getName();
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("number of properties", 2, builder.getProperties().size());
	}

	@Test
	public void testItemsProperty() {
		// Given:
		String pojoClassname = Order.class.getName();
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilder();

		// Then:
        assertThat(builder.getProperties(), hasItem(
                propertyM(named("items"), withType("testdata.array.Item[]"), withSetter("setItems"))
        ));
	}
}
