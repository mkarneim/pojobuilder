package study.buildermodelproducer.simple;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.TypeM;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import study.buildermodelproducer.BuilderModelProducer;
import study.buildermodelproducer.TestData;
import testenv.AddSourceFile;
import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
@AddSourceFile({ TestData.SRC_TESTDATA_CONTACT })
public class BuilderModelProducerTest {

	private static final String CLS_TESTDATA_CONTACTBUILDER = "testdata.ContactBuilder";

	private ProcessingEnvironment env;

	private BuilderModelProducer underTest;

	@Before
	public void setup() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		underTest = new BuilderModelProducer(env);
	}

	@Test
	public void testProduceModelReturnsModelWithProductType() {
		// Given:
		String pojoClassname = TestData.CLS_TESTDATA_CONTACT;
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		BuilderM result = underTest.produceModel(pojoTypeElement);

		// Then:
		Assert.assertEquals("productType", TypeM.get(pojoClassname), result.getProductType());
	}

	@Test
	public void testProduceModelReturnsModelWithBuilderType() {
		// Given:
		String pojoClassname = TestData.CLS_TESTDATA_CONTACT;
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		BuilderM result = underTest.produceModel(pojoTypeElement);

		// Then:
		Assert.assertEquals("type", TypeM.get(CLS_TESTDATA_CONTACTBUILDER), result.getType());
	}

	@Test
	public void testProduceModelReturnsModelWithSuperType() {
		// Given:
		String pojoClassname = TestData.CLS_TESTDATA_CONTACT;
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		BuilderM result = underTest.produceModel(pojoTypeElement);

		// Then:
		Assert.assertEquals("type", TypeM.get(TestData.CLS_OBJECT), result.getSuperType());
	}

}
