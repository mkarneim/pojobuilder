package study.buildermodelproducer;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import net.karneim.pojobuilder.model.BuilderM;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;
import testenv.TestBase;
import destdata.TestData;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestData.SRC_SAMPLES_DIR })
public class SimpleBuilderPackageTest extends TestBase {
	private static String ADDRESS_DTO_CLASSNAME = samples.with.builderpackage.AddressDTO.class.getName();

	private ProcessingEnvironment env;

	private BuilderModelProducer underTest;

	@Before
	public void setup() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		TypeMUtils typeMUtils = new TypeMUtils();
		underTest = new BuilderModelProducer(env, typeMUtils);
	}

	@Test
	public void testBuilderPackage() {
		// Given:
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(ADDRESS_DTO_CLASSNAME);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("builder classname", "AddressDTOBuilder", builder.getType().getSimpleName());
		assertEquals("package", "samples.with.builderpackage.builder", builder.getType().getPackage());
	}
}
