package net.karneim.pojobuilder;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testdata.basic.Sample;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import static net.karneim.pojobuilder.matchers.PBMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class BasicTest extends TestBase {

    private static final String TESTDATA_BASIC_SAMPLE_BUILDER = "testdata.basic.SampleBuilder";

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
    public void testProduceReturnsBuilderWithCorrectProductType() {
        // Given:
        String pojoClassname = Sample.class.getCanonicalName();
        TypeElement pojoType = elements.getTypeElement(pojoClassname);

        // When:
        Output output = underTest.produce(new Input(pojoType));
        BuilderM builder = output.getBuilder();

        // Then:
        assertEquals("productType", TypeM.get(pojoClassname), builder.getProductType());
    }

    @Test
    public void testProduceReturnsBuilderWithCorrectBuilderType() {
        // Given:
        String pojoClassname = Sample.class.getCanonicalName();
        TypeElement pojoType = elements.getTypeElement(pojoClassname);

        // When:
        Output output = underTest.produce(new Input(pojoType));
        BuilderM builder = output.getBuilder();

        // Then:
        assertEquals("type", TypeM.get(TESTDATA_BASIC_SAMPLE_BUILDER), builder.getType());
    }

    @Test
    public void testProduceReturnsBuilderWithCorrectSuperType() {
        // Given:
        String pojoClassname = Sample.class.getCanonicalName();
        TypeElement pojoType = elements.getTypeElement(pojoClassname);

        // When:
        Output output = underTest.produce(new Input(pojoType));
        BuilderM builder = output.getBuilder();

        // Then:
        assertEquals("type", TypeM.get(Object.class.getCanonicalName()), builder.getSuperType());
    }

    @Test
    public void testProduceReturnsBuilderWithCorrectSelfType() {
        // Given:
        String pojoClassname = Sample.class.getCanonicalName();
        TypeElement pojoType = elements.getTypeElement(pojoClassname);

        // When:
        Output output = underTest.produce(new Input(pojoType));
        BuilderM builder = output.getBuilder();

        // Then:
        assertEquals("selfType", TypeM.get(TESTDATA_BASIC_SAMPLE_BUILDER), builder.getSelfType());
    }

    @Test
    public void testProduceReturnsBuilderWithCorrectProperties() {
        // Given:
        String pojoClassname = Sample.class.getCanonicalName();
        TypeElement pojoType = elements.getTypeElement(pojoClassname);

        // When:
        Output output = underTest.produce(new Input(pojoType));
        BuilderM builder = output.getBuilder();

        // Then:
        assertThat(builder.getProperties(), containsOnly(
                propertyM(named("name"), withType("java.lang.String")),
                propertyM(named("email"), withType("java.lang.String")),
                propertyM(named("number"), withType("int")),
                propertyM(named("active"), withType("boolean"), withSetter("setActive")),
                propertyM(named("value"), withType("java.lang.Long"), withSetter("setValue")),
                propertyM(named("strings"), withType("java.util.List"), withSetter("setStrings")),
                propertyM(named("constrField"), withType("java.lang.String"), withPosition(0))
        ));
        PropertyM p5 = getFirstPropertyByName(builder.getProperties(), "strings");
        assertEquals("strings.genericType", "List<String>", p5.getType().getGenericTypeSimpleNameWithBounds());
        assertEquals("strings.typeParameter(0).type", "java.lang.String", p5.getType().getTypeParameters().get(0)
                .getType().getQualifiedName());

        assertEquals("selfType", TypeM.get(TESTDATA_BASIC_SAMPLE_BUILDER), builder.getSelfType());
    }

}
