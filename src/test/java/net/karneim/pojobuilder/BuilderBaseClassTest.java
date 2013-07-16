package net.karneim.pojobuilder;

import net.karneim.pojobuilder.model.BuilderM;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testdata.baseclass.Contact;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import static org.junit.Assert.assertEquals;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class BuilderBaseClassTest extends TestBase {
    private static String CONTACT_CLASSNAME = Contact.class.getCanonicalName();

    private ProcessingEnvironment env;

	private GeneratePojoBuilderProcessor underTest;

    @Before
    public void setup() {
        env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		underTest = new GeneratePojoBuilderProcessor(env);
    }

    @Test
    public void testBuilderClassname() {
        TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(CONTACT_CLASSNAME);

        // When:
		Output output = underTest.testProcess(pojoTypeElement);
        BuilderM builder = output.getBuilder();

        // Then:
        assertEquals("builder classname", "ContactBuilder", builder.getType().getSimpleName());
    }

    @Test
    public void testBuilderBaseclass() {
        // Given:
        TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(CONTACT_CLASSNAME);

        // When:
        Output output = underTest.testProcess(pojoTypeElement);
        BuilderM builder = output.getBuilder();

        // Then:
        assertEquals("supertype", "BaseBuilder", builder.getSuperType().getSimpleName());
    }
}
