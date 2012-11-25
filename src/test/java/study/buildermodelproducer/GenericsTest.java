package study.buildermodelproducer;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import net.karneim.pojobuilder.model.BuilderM;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testenv.AddSourceFile;
import testenv.ProcessingEnvironmentRunner;
import destdata.TestData;

@RunWith(ProcessingEnvironmentRunner.class)
@AddSourceFile({ TestData.SRC_TESTDATA_GENERICS_NUMBER_GRID })
public class GenericsTest {

	private ProcessingEnvironment env;

	private BuilderModelProducer underTest;

	@Before
	public void setup() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		TypeMUtils typeMUtils = new TypeMUtils();
		underTest = new BuilderModelProducer(env, typeMUtils);
	}

	@Test
	public void testProduceModelReturnsModelWithTypeParameters() {
		// Given:
		String pojoClassname = TestData.CLS_TESTDATA_GENERICS_NUMBER_GRID;
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilderModel();

		// Then:
		Assert.assertEquals("type", TestData.GENERIC_NUMBER_GRID_BUIDLER, builder.getType().getGenericTypeSimpleNameWithBounds());
		
		
	}

}
