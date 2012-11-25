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
@AddSourceFile({ TestData.SRC_TESTDATA_BASECLASS_CONTACT, TestData.SRC_TESTDATA_BASECLASS_BASE_BUILDER })
public class WithBaseclassTest {

	private ProcessingEnvironment env;

	private BuilderModelProducer underTest;

	@Before
	public void setup() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		TypeMUtils typeMUtils = new TypeMUtils();
		underTest = new BuilderModelProducer(env, typeMUtils);
	}

	@Test
	public void testProduceReturnsModelWithSpecifiedSuperType() {
		// Given:
		String pojoClassname = TestData.CLS_TESTDATA_BASECLASS_CONTACT;
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilderModel();

		// Then:
		Assert.assertEquals("superType", TypeM.get(TestData.CLS_TESTDATA_BASECLASS_BASE_BUILDER), builder.getSuperType());
	}

}
