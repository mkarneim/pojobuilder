package study.buildermodelproducer.baseclass;

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
@AddSourceFile({ TestData.SRC_TESTDATA_BASECLASS_CONTACT, TestData.SRC_TESTDATA_BASECLASS_BASE_BUILDER })
public class BuilderModelProducerTest {

	private ProcessingEnvironment env;

	private BuilderModelProducer underTest;

	@Before
	public void setup() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		underTest = new BuilderModelProducer(env);
	}

	@Test
	public void testProduceModelReturnsModelWithSuperType() {
		// Given:
		String pojoClassname = TestData.CLS_TESTDATA_BASECLASS_CONTACT;
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		BuilderM result = underTest.produceModel(pojoTypeElement);

		// Then:
		Assert.assertEquals("type", TypeM.get(TestData.CLS_TESTDATA_BASECLASS_BASE_BUILDER), result.getSuperType());
	}

}
