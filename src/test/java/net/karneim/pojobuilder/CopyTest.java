package net.karneim.pojobuilder;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.TypeM;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testdata.copy.AddressDTO;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class CopyTest extends TestBase {

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
		String pojoClassname = AddressDTO.class.getCanonicalName();
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
		String pojoClassname = AddressDTO.class.getCanonicalName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoType));
		BuilderM builder = output.getBuilder();

		// Then:

		assertThat(builder.getPropertiesForCopy(), containsPropertyWithName("name"));
		assertThat(builder.getPropertiesForCopy(), containsPropertyWithName("street"));
		assertThat(builder.getPropertiesForCopy(), containsPropertyWithName("city"));
		assertThat(builder.getPropertiesForCopy(), containsPropertyWithName("postCode"));
		assertThat(builder.getPropertiesForCopy(), containsPropertyWithName("forSale"));

		// NOT assertThat(builder.getProperties(),
		// containsPropertyWithName("surname"));
	}

}
