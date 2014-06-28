package net.karneim.pojobuilder;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

import java.util.TreeSet;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import net.karneim.pojobuilder.model.BuilderM;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testdata.generics.NumberGrid;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class ListTest extends TestBase {
	
	private ProcessingEnvironment env;

    private BuilderModelProducer underTest;

    @Before
    public void setup() {
        env = ProcessingEnvironmentRunner.getProcessingEnvironment();
        TypeMUtils typeMUtils = new TypeMUtils();
        underTest = new BuilderModelProducer(env, typeMUtils);
    }

	@Test
    public void testProduceModelReturnsModelThatDetectsLists() {
        // Given:
        String pojoClassname = NumberGrid.class.getCanonicalName();
        TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

        // When:
        Output output = underTest.produce(new Input(pojoTypeElement));
        BuilderM builder = output.getBuilder();

        // Then:
        assertTrue("isList", builder.getProperties().iterator().next().getType().isList());
    }
	
	@Test
    public void testProduceModelReturnsModelThatAddListSpecificImports() {
        // Given:
        String pojoClassname = NumberGrid.class.getCanonicalName();
        TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);
        TreeSet<String> additionalImports = new TreeSet<String>();

        // When:
        Output output = underTest.produce(new Input(pojoTypeElement));
        BuilderM builder = output.getBuilder();
        builder.getProperties().iterator().next().getType().addToImportTypes(additionalImports);

        // Then:
		assertArrayEquals(
			new String[]{"java.util.ArrayList", "java.util.Arrays", "java.util.Collection", "java.util.List"},
			additionalImports.toArray(new String[0]));
    }

}
