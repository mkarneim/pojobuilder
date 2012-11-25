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
@AddSourceFile({ TestData.SRC_TESTDATA_INTO_PACKAGE_CONTACT })
public class IntoPackageTest {

	private ProcessingEnvironment env;

	private BuilderModelProducer underTest;

	@Before
	public void setup() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		TypeMUtils typeMUtils = new TypeMUtils();
		underTest = new BuilderModelProducer(env, typeMUtils);
	}

	@Test
	public void testProduceModelReturnsModelWithTargetPackage() {
		// Given:
		String pojoClassname = TestData.CLS_TESTDATA_INTO_PACKAGE_CONTACT;
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilderModel();

		// Then:
		Assert.assertEquals("type", TypeM.get(TestData.CLS_TESTDATA_INTO_PACKAGE_TARGET_CONTACT_BUILDER), builder.getType());
	}

}
