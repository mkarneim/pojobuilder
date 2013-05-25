package net.karneim.pojobuilder;

import java.net.MalformedURLException;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.TypeM;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testdata.exception.Resource;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class ExceptionTest extends TestBase {

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
		String pojoClassname = Resource.class.getName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoType));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("productType", TypeM.get(pojoClassname), builder.getProductType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectBuildExceptions() {
		// Given:
		String pojoClassname = Resource.class.getName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoType));
		BuilderM builder = output.getBuilder();

		// Then:
		assertThat(builder.getBuildExceptions(), contains(TypeM.get(MalformedURLException.class.getName())));
	}

}
