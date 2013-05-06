package net.karneim.pojobuilder;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import net.karneim.pojobuilder.model.BuilderM;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testdata.intoPackage.AddressDTO;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class IntoPackageTest extends TestBase {
	private static String ADDRESS_DTO_CLASSNAME = AddressDTO.class.getName();

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
		assertEquals("package", "testdata.builder", builder.getType().getPackage());
	}
}
