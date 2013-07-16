package net.karneim.pojobuilder;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.ManualBuilderM;
import net.karneim.pojobuilder.model.TypeM;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class UseGenerationGapTest extends TestBase {

	private Elements elements;

	private GeneratePojoBuilderProcessor underTest;

	@Before
	public void setup() {
		ProcessingEnvironment env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		elements = env.getElementUtils();
        underTest = new GeneratePojoBuilderProcessor(env);
	}

	@Test
	public void testProduceReturnsOutputWithGenerationGap1() {
		// Given:
        String pojoClassname = testdata.generationgap.Contact.class.getCanonicalName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
        Output output = underTest.testProcess(pojoType);
		BuilderM builder = output.getBuilder();
		ManualBuilderM manualBuilder = output.getManualBuilder();

		// Then:
		assertEquals("type", TypeM.get("testdata.generationgap.ContactBuilder"), manualBuilder.getType());
		assertEquals("superType", TypeM.get("testdata.generationgap.AbstractContactBuilder"),
				manualBuilder.getSuperType());
		assertEquals("type", TypeM.get("testdata.generationgap.AbstractContactBuilder"), builder.getType());
        assertThat(builder.getSuperType(), is(nullValue()));
	}

	@Test
	public void testProduceReturnsOutputWithGenerationGap2() {
		// Given:
        String pojoClassname = testdata.generationgap.Order.class.getCanonicalName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
        Output output = underTest.testProcess(pojoType);
		BuilderM builder = output.getBuilder();
		ManualBuilderM manualBuilder = output.getManualBuilder();

		// Then:
		assertEquals("type", TypeM.get("testdata.generationgap.OrderBuilder"), manualBuilder.getType());
		assertEquals("type.generic", "OrderBuilder<T extends Object>", manualBuilder.getType()
				.getGenericTypeSimpleNameWithBounds());
		assertEquals("superType", TypeM.get("testdata.generationgap.AbstractOrderBuilder"),
				manualBuilder.getSuperType());
		assertEquals("type", TypeM.get("testdata.generationgap.AbstractOrderBuilder"), builder.getType());
		assertEquals("type.generic", "AbstractOrderBuilder<T extends Object>", builder.getType()
				.getGenericTypeSimpleNameWithBounds());
        assertThat(builder.getSuperType(), is(nullValue()));
	}

}
