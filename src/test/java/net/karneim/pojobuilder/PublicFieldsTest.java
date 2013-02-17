package net.karneim.pojobuilder;

import java.util.Collection;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import net.karneim.pojobuilder.BuilderModelProducer;
import net.karneim.pojobuilder.Input;
import net.karneim.pojobuilder.Output;
import net.karneim.pojobuilder.TypeMUtils;
import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import samples.bestpractice.Item;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestData.SRC_SAMPLES_DIR })
public class PublicFieldsTest extends TestBase {

	private static final String ITEM_BUILDER = "samples.bestpractice.ItemBuilder";

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
	public void testProduceReturnsBuilderWithCorrectProductType() {
		// Given:
		String pojoClassname = Item.class.getName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoType));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("productType", TypeM.get(pojoClassname), builder.getProductType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectBuilderType() {
		// Given:
		String pojoClassname = Item.class.getName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoType));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("type", TypeM.get(ITEM_BUILDER), builder.getType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectSuperType() {
		// Given:
		String pojoClassname = Item.class.getName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoType));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("type", TypeM.get(Object.class.getName()), builder.getSuperType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectSelfType() {
		// Given:
		String pojoClassname = Item.class.getName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoType));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("selfType", TypeM.get(ITEM_BUILDER), builder.getSelfType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectProperties() {
		// Given:
		String pojoClassname = Item.class.getName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoType));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("size", 2, builder.getProperties().size());
		assertThat(builder.getProperties(), containsPropertyWithName("name"));
		assertThat(builder.getProperties(), containsPropertyWithName("amount"));

		PropertyM p0 = filterByName(builder.getProperties(), "name").get(0);
		assertEquals("name.type", "java.lang.String", p0.getType().getQualifiedName());
		PropertyM p1 = filterByName(builder.getProperties(), "amount").get(0);
		assertEquals("email.type", "int", p1.getType().getQualifiedName());

		assertEquals("selfType", TypeM.get(ITEM_BUILDER), builder.getSelfType());

		Collection<PropertyM> setterProps = builder.getPropertiesForSetters();
		assertEquals("size of properties to set", 0, setterProps.size());
		
		Collection<PropertyM> assignProps = builder.getPropertiesForAssignment();
		assertEquals("size of properties to assign", 2, assignProps.size());
	}

}
