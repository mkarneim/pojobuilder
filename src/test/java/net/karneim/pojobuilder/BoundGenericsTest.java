package net.karneim.pojobuilder;


import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import net.karneim.pojobuilder.BuilderModelProducer;
import net.karneim.pojobuilder.Input;
import net.karneim.pojobuilder.Output;
import net.karneim.pojobuilder.TypeMUtils;
import net.karneim.pojobuilder.model.BuilderM;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestData.SRC_SAMPLES_DIR })
public class BoundGenericsTest extends TestBase {

	private ProcessingEnvironment env;

	private BuilderModelProducer underTest;

	@Before
	public void setup() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		TypeMUtils typeMUtils = new TypeMUtils();
		underTest = new BuilderModelProducer(env, typeMUtils);
	}

	@Test
	public void testProduceModelReturnsModelWithTypeParameters() {
		// Given:
		String pojoClassname = samples.with.generics.Container.class.getName();
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("type", "ContainerBuilder<T extends Item & Serializable>", builder.getType()
				.getGenericTypeSimpleNameWithBounds());
	
		// When:
		Set<String> imports = new HashSet<String>();
		builder.addToImportTypes(imports);
		
		// Then
		assertThat( imports, contains("java.io.Serializable"));
		
	}
	
	
}
