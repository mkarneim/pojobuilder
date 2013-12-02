package net.karneim.pojobuilder;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.modelproducers.BuilderModelProducer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import static org.junit.Assert.assertEquals;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class IntoPackageWithWildcardTest extends TestBase {
    private static String CONTACT_CLASSNAME = testdata.intoPackage.wildcard.Contact.class.getCanonicalName();

    private ProcessingEnvironment env;

    private GeneratePojoBuilderProcessor underTest;

    @Before
    public void setup() {
        env = ProcessingEnvironmentRunner.getProcessingEnvironment();
        underTest = new GeneratePojoBuilderProcessor(env);
    }

    @Test
    public void testBuilderPackage() {
        // Given:
        TypeElement pojoType = env.getElementUtils().getTypeElement(CONTACT_CLASSNAME);

        // When:
        Output output = underTest.testProcess(pojoType);
        BuilderM builder = output.getBuilder();

        // Then:
        assertEquals("builder classname", "ContactBuilder", builder.getType().getSimpleName());
        assertEquals("package", "testdata.intoPackage.wildcard.builder", builder.getType().getPackage());
    }
}
