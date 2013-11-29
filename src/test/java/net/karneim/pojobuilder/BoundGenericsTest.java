package net.karneim.pojobuilder;

import net.karneim.pojobuilder.model.BuilderM;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testdata.generics.Container;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
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
        String pojoClassname = Container.class.getCanonicalName();
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
        assertThat(imports, hasItem("java.io.Serializable"));

    }

}
