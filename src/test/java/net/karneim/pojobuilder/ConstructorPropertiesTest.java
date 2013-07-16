package net.karneim.pojobuilder;

import net.karneim.pojobuilder.model.BuilderM;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import static net.karneim.pojobuilder.matchers.PBMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class ConstructorPropertiesTest extends TestBase {
    private static String ADDRESS_DTO_CLASSNAME = testdata.constructorproperties.AddressDTO.class.getCanonicalName();

	private ProcessingEnvironment env;

	private GeneratePojoBuilderProcessor underTest;

	@Before
	public void setup() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
        underTest = new GeneratePojoBuilderProcessor(env);
	}

	@Test
	public void testConstrucorParameterPositions() {
		// Given:
		TypeElement pojoType = env.getElementUtils().getTypeElement(ADDRESS_DTO_CLASSNAME);

		// When:
        Output output = underTest.testProcess(pojoType);
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("builder classname", "AddressDTOBuilder", builder.getType().getSimpleName());
        assertThat(builder.getProperties(), containsOnly(
                propertyM(named("name"), withPosition(0)),
                propertyM(named("street"), withPosition(1)),
                propertyM(named("city"), withPosition(2)),
                propertyM(named("postCode"), withPosition(3))
        ));

	}
}
