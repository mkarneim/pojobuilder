package study.buildermodelproducer;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testdata.basic.Sample;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;
import testenv.TestBase;
import destdata.TestData;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestData.SRC_TESTDATA_DIR })
public class BasicTest extends TestBase {

	private static final String TESTDATA_BASIC_SAMPLE_BUILDER = "testdata.basic.SampleBuilder";

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
		String pojoClassname = Sample.class.getName();
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
		String pojoClassname = Sample.class.getName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoType));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("type", TypeM.get(TESTDATA_BASIC_SAMPLE_BUILDER), builder.getType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectSuperType() {
		// Given:
		String pojoClassname = Sample.class.getName();
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
		String pojoClassname = Sample.class.getName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoType));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("selfType", TypeM.get(TESTDATA_BASIC_SAMPLE_BUILDER), builder.getSelfType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectProperties() {
		// Given:
		String pojoClassname = Sample.class.getName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoType));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("size", 7, builder.getProperties().size());
		assertThat(builder.getProperties(), containsPropertyWithName("name"));
		assertThat(builder.getProperties(), containsPropertyWithName("email"));
		assertThat(builder.getProperties(), containsPropertyWithName("number"));
		assertThat(builder.getProperties(), containsPropertyWithName("active"));
		assertThat(builder.getProperties(), containsPropertyWithName("value"));
		assertThat(builder.getProperties(), containsPropertyWithName("strings"));
		assertThat(builder.getProperties(), containsPropertyWithName("constrField"));

		PropertyM p0 = filterByName(builder.getProperties(), "name").get(0);
		assertEquals("name.type", "java.lang.String", p0.getType().getQualifiedName());
		PropertyM p1 = filterByName(builder.getProperties(), "email").get(0);
		assertEquals("email.type", "java.lang.String", p1.getType().getQualifiedName());
		PropertyM p2 = filterByName(builder.getProperties(), "number").get(0);
		assertEquals("number.type", "int", p2.getType().getQualifiedName());
		PropertyM p3 = filterByName(builder.getProperties(), "active").get(0);
		assertEquals("active.type", "boolean", p3.getType().getQualifiedName());
		assertEquals("active.setter", "setActive", p3.getSetter());
		PropertyM p4 = filterByName(builder.getProperties(), "value").get(0);
		assertEquals("value.type", "java.lang.Long", p4.getType().getQualifiedName());
		assertEquals("value.setter", "setValue", p4.getSetter());
		PropertyM p5 = filterByName(builder.getProperties(), "strings").get(0);
		assertEquals("strings.type", "java.util.List", p5.getType().getQualifiedName());
		assertEquals("strings.genericType", "List<String>", p5.getType().getGenericTypeSimpleNameWithBounds());
		assertEquals("strings.typeParameter(0).type", "java.lang.String", p5.getType().getTypeParameters().get(0)
				.getType().getQualifiedName());
		assertEquals("strings.setter", "setStrings", p5.getSetter());
		PropertyM p6 = filterByName(builder.getProperties(), "constrField").get(0);
		assertEquals("constrField.type", "java.lang.String", p6.getType().getQualifiedName());
		assertEquals("constrField.parameterPos", 0, p6.getParameterPos().intValue());

		assertEquals("selfType", TypeM.get(TESTDATA_BASIC_SAMPLE_BUILDER), builder.getSelfType());
	}

}
