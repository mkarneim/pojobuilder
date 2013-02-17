package study.buildermodelproducer;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.PropertyM;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;
import destdata.TestData;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestData.SRC_SAMPLES_DIR })
public class ConstructorPropertiesTest extends TestBase {
	private static String ADDRESS_DTO_CLASSNAME = samples.with.constructorproperties.AddressDTO.class.getName();

	private ProcessingEnvironment env;

	private BuilderModelProducer underTest;

	@Before
	public void setup() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		TypeMUtils typeMUtils = new TypeMUtils();
		underTest = new BuilderModelProducer(env, typeMUtils);
	}

	@Test
	public void testConstrucorParameterPositions() {
		// Given:
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(ADDRESS_DTO_CLASSNAME);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("builder classname", "AddressDTOBuilder", builder.getType().getSimpleName());
		assertThat(builder.getProperties(), containsPropertyWithName("name"));
		assertThat(builder.getProperties(), containsPropertyWithName("street"));
		assertThat(builder.getProperties(), containsPropertyWithName("city"));
		assertThat(builder.getProperties(), containsPropertyWithName("postCode"));
		PropertyM p0 = filterByName(builder.getProperties(), "name").get(0);
		assertEquals("constructor parameter pos", 0, (int) p0.getParameterPos());
		PropertyM p1 = filterByName(builder.getProperties(), "street").get(0);
		assertEquals("constructor parameter pos", 1, (int) p1.getParameterPos());
		PropertyM p2 = filterByName(builder.getProperties(), "city").get(0);
		assertEquals("constructor parameter pos", 2, (int) p2.getParameterPos());
		PropertyM p3 = filterByName(builder.getProperties(), "postCode").get(0);
		assertEquals("constructor parameter pos", 3, (int) p3.getParameterPos());
	}
}
