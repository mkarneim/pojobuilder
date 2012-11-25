package study.buildermodelproducer;

import static testenv.TestHelpers.containsPropertyWithName;
import static testenv.TestHelpers.filterByName;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testenv.AddSourceFile;
import testenv.ProcessingEnvironmentRunner;
import destdata.TestData;

@RunWith(ProcessingEnvironmentRunner.class)
@AddSourceFile({ TestData.SRC_TESTDATA_SAMPLE, TestData.SRC_TESTDATA_PARENT })
public class BasicTest {

	private ProcessingEnvironment env;

	private BuilderModelProducer underTest;

	@Before
	public void setup() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		TypeMUtils typeMUtils = new TypeMUtils();
		underTest = new BuilderModelProducer(env, typeMUtils);
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectProductType() {
		// Given:
		String pojoClassname = TestData.CLS_TESTDATA_SAMPLE;
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilderModel();

		// Then:
		Assert.assertEquals("productType", TypeM.get(pojoClassname), builder.getProductType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectBuilderType() {
		// Given:
		String pojoClassname = TestData.CLS_TESTDATA_SAMPLE;
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilderModel();

		// Then:
		Assert.assertEquals("type", TypeM.get(TestData.CLS_TESTDATA_SAMPLE_BUILDER), builder.getType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectSuperType() {
		// Given:
		String pojoClassname = TestData.CLS_TESTDATA_SAMPLE;
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilderModel();

		// Then:
		Assert.assertEquals("type", TypeM.get(TestData.CLS_OBJECT), builder.getSuperType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectSelfType() {
		// Given:
		String pojoClassname = TestData.CLS_TESTDATA_SAMPLE;
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilderModel();

		// Then:
		Assert.assertEquals("selfType", TypeM.get(TestData.CLS_TESTDATA_SAMPLE_BUILDER), builder.getSelfType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectProperties() {
		// Given:
		String pojoClassname = TestData.CLS_TESTDATA_SAMPLE;
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilderModel();

		// Then:
		Assert.assertEquals("size", 7, builder.getProperties().size());
		Assert.assertThat(builder.getProperties(), containsPropertyWithName("name"));
		Assert.assertThat(builder.getProperties(), containsPropertyWithName("email"));
		Assert.assertThat(builder.getProperties(), containsPropertyWithName("number"));
		Assert.assertThat(builder.getProperties(), containsPropertyWithName("active"));
		Assert.assertThat(builder.getProperties(), containsPropertyWithName("value"));
		Assert.assertThat(builder.getProperties(), containsPropertyWithName("strings"));
		Assert.assertThat(builder.getProperties(), containsPropertyWithName("constrField"));

		PropertyM p0 = filterByName(builder.getProperties(), "name").get(0);
		Assert.assertEquals("name.type", "java.lang.String", p0.getType().getQualifiedName());
		PropertyM p1 = filterByName(builder.getProperties(), "email").get(0);
		Assert.assertEquals("email.type", "java.lang.String", p1.getType().getQualifiedName());
		PropertyM p2 = filterByName(builder.getProperties(), "number").get(0);
		Assert.assertEquals("number.type", "int", p2.getType().getQualifiedName());
		PropertyM p3 = filterByName(builder.getProperties(), "active").get(0);
		Assert.assertEquals("active.type", "boolean", p3.getType().getQualifiedName());
		Assert.assertEquals("active.setter", "setActive", p3.getSetter());
		PropertyM p4 = filterByName(builder.getProperties(), "value").get(0);
		Assert.assertEquals("value.type", "java.lang.Long", p4.getType().getQualifiedName());
		Assert.assertEquals("value.setter", "setValue", p4.getSetter());
		PropertyM p5 = filterByName(builder.getProperties(), "strings").get(0);
		Assert.assertEquals("strings.type", "java.util.List", p5.getType().getQualifiedName());
		Assert.assertEquals("strings.genericType", "List<String>", p5.getType().getGenericTypeSimpleNameWithBounds());
		Assert.assertEquals("strings.typeParameter(0).type", "java.lang.String", p5.getType().getTypeParameters().get(0).getType().getQualifiedName());
		Assert.assertEquals("strings.setter", "setStrings", p5.getSetter());
		PropertyM p6 = filterByName(builder.getProperties(), "constrField").get(0);
		Assert.assertEquals("constrField.type", "java.lang.String", p6.getType().getQualifiedName());
		Assert.assertEquals("constrField.parameterPos", 0, p6.getParameterPos().intValue());
		
		Assert.assertEquals("selfType", TypeM.get(TestData.CLS_TESTDATA_SAMPLE_BUILDER), builder.getSelfType());
	}

}
