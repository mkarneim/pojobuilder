package study.buildermodelproducer;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.TypeM;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import destdata.TestData;

import testenv.AddSourceFile;
import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
public class UseGenerationGapTest {

	private ProcessingEnvironment env;

	private BuilderModelProducer underTest;

	@Before
	public void setup() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		TypeMUtils typeMUtils = new TypeMUtils();
		underTest = new BuilderModelProducer(env, typeMUtils);
	}

	@Test
	@AddSourceFile({ TestData.SRC_TESTDATA_GENERATIONGAP_CONTACT })
	public void testProduceReturnsOutputWithGenerationGap1() {
		// Given:
		String pojoClassname = TestData.CLS_TESTDATA_GENERATIONGAP_CONTACT;
		;
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilderModel();
		ManualBuilderM manualBuilder = output.getManualBuilderModel();

		// Then:
		Assert.assertEquals("type", TypeM.get(TestData.CLS_TESTDATA_GENERATIONGAP_CONTACT_BUILDER),
				manualBuilder.getType());
		Assert.assertEquals("superType", TypeM.get(TestData.CLS_TESTDATA_GENERATIONGAP_ABSTRACT_CONTACT_BUILDER),
				manualBuilder.getSuperType());
		Assert.assertEquals("type", TypeM.get(TestData.CLS_TESTDATA_GENERATIONGAP_ABSTRACT_CONTACT_BUILDER),
				builder.getType());
		Assert.assertEquals("superType", TypeM.get(TestData.CLS_OBJECT), builder.getSuperType());
	}

	@Test
	@AddSourceFile({ TestData.SRC_TESTDATA_GENERATIONGAP_ORDER })
	public void testProduceReturnsOutputWithGenerationGap2() {
		// Given:
		String pojoClassname = TestData.CLS_TESTDATA_GENERATIONGAP_ORDER;
		;
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilderModel();
		ManualBuilderM manualBuilder = output.getManualBuilderModel();

		// Then:
		Assert.assertEquals("type", TypeM.get(TestData.CLS_TESTDATA_GENERATIONGAP_ORDER_BUILDER),
				manualBuilder.getType());
		Assert.assertEquals("type.generic", "OrderBuilder<T extends Object>", manualBuilder.getType()
				.getGenericTypeSimpleNameWithBounds());
		Assert.assertEquals("superType", TypeM.get(TestData.CLS_TESTDATA_GENERATIONGAP_ABSTRACT_ORDER_BUILDER),
				manualBuilder.getSuperType());
		Assert.assertEquals("type", TypeM.get(TestData.CLS_TESTDATA_GENERATIONGAP_ABSTRACT_ORDER_BUILDER),
				builder.getType());
		Assert.assertEquals("type.generic", "AbstractOrderBuilder<T extends Object>", builder.getType()
				.getGenericTypeSimpleNameWithBounds());
		Assert.assertEquals("superType", TypeM.get(TestData.CLS_OBJECT), builder.getSuperType());
	}

}
