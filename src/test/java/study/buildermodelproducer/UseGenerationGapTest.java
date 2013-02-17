package study.buildermodelproducer;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.TypeM;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;
import testenv.TestBase;
import destdata.TestData;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestData.SRC_TESTDATA_DIR })
public class UseGenerationGapTest extends TestBase {

	private ProcessingEnvironment env;

	private BuilderModelProducer underTest;

	@Before
	public void setup() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		TypeMUtils typeMUtils = new TypeMUtils();
		underTest = new BuilderModelProducer(env, typeMUtils);
	}

	@Test
	public void testProduceReturnsOutputWithGenerationGap1() {
		// Given:
		String pojoClassname = testdata.generationgap.Contact.class.getName();
		;
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilder();
		ManualBuilderM manualBuilder = output.getManualBuilder();

		// Then:
		assertEquals("type", TypeM.get("testdata.generationgap.ContactBuilder"), manualBuilder.getType());
		assertEquals("superType", TypeM.get("testdata.generationgap.AbstractContactBuilder"),
				manualBuilder.getSuperType());
		assertEquals("type", TypeM.get("testdata.generationgap.AbstractContactBuilder"), builder.getType());
		assertEquals("superType", TypeM.get(Object.class.getName()), builder.getSuperType());
	}

	@Test
	public void testProduceReturnsOutputWithGenerationGap2() {
		// Given:
		String pojoClassname = testdata.generationgap.Order.class.getName();
		;
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

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
		assertEquals("superType", TypeM.get(Object.class.getName()), builder.getSuperType());
	}

}
