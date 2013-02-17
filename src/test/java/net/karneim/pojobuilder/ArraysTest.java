package net.karneim.pojobuilder;


import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import net.karneim.pojobuilder.BuilderModelProducer;
import net.karneim.pojobuilder.Input;
import net.karneim.pojobuilder.Output;
import net.karneim.pojobuilder.TypeMUtils;
import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.PropertyM;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestData.SRC_SAMPLES_DIR })
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
		String pojoClassname = samples.with.arrays.Order.class.getName();
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
		String pojoClassname = samples.with.arrays.Order.class.getName();
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
		String pojoClassname = samples.with.arrays.Order.class.getName();
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilder();
		
		// Then:
		assertThat(builder.getProperties(), containsPropertyWithName("items"));
		PropertyM p0 = filterByName(builder.getProperties(), "items").get(0);
		assertEquals("property type", "Item[]", p0.getType().getSimpleName());
		assertEquals("setter", "setItems", p0.getSetter());
	}
}
