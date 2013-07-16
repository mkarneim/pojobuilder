package net.karneim.pojobuilder;

import net.karneim.pojobuilder.model.BuilderM;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testdata.generics.NumberGrid;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import static org.junit.Assert.assertEquals;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class GenericsTest extends TestBase {

    private ProcessingEnvironment env;

	private GeneratePojoBuilderProcessor underTest;

	@Before
	public void setup() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
        underTest = new GeneratePojoBuilderProcessor(env);
	}

	@Test
	public void testProduceModelReturnsModelWithTypeParameters() {
		// Given:
        String pojoClassname = NumberGrid.class.getCanonicalName();
		TypeElement pojoType = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
        Output output = underTest.testProcess(pojoType);
		BuilderM builder = output.getBuilder();

        // Then:
        assertEquals("type", "NumberGridBuilder<E extends Number>", builder.getType()
                .getGenericTypeSimpleNameWithBounds());

    }

}
