package net.karneim.pojobuilder;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.ManualBuilderM;
import net.karneim.pojobuilder.model.TypeM;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class UseGenerationGapTest extends TestBase {

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
	public void testProduceReturnsOutputWithGenerationGap1() {
		// Given:
		String pojoClassname = testdata.generationgap.Contact.class.getCanonicalName();

		TypeElement pojoTypeElement = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilder();
		ManualBuilderM manualBuilder = output.getManualBuilder();

		// Then:
		assertEquals("type", TypeM.get("testdata.generationgap.ContactBuilder"), manualBuilder.getType());
		assertEquals("superType", TypeM.get("testdata.generationgap.AbstractContactBuilder"),
				manualBuilder.getSuperType());
		assertEquals("type", TypeM.get("testdata.generationgap.AbstractContactBuilder"), builder.getType());
		assertEquals("superType", TypeM.get(Object.class.getCanonicalName()), builder.getSuperType());
	}

	@Test
	public void testProduceReturnsOutputWithGenerationGap2() {
		// Given:
		String pojoClassname = testdata.generationgap.Order.class.getCanonicalName();

		TypeElement pojoTypeElement = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
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
		assertEquals("superType", TypeM.get(Object.class.getCanonicalName()), builder.getSuperType());
	}

}
