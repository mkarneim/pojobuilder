package net.karneim.pojobuilder;


import net.karneim.pojobuilder.model.BuilderM;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testdata.constructor.ClassLevelAnnotation;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import static net.karneim.pojobuilder.matchers.PBMatchers.containsPropertyWithName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class ConstructorSelectionTest extends TestBase {
	private static String APPLE_CLASSNAME = ClassLevelAnnotation.Apple.class.getCanonicalName();
    private static String BANANA_CLASSNAME = ClassLevelAnnotation.Banana.class.getCanonicalName();
    private static String CHERRY_CLASSNAME = ClassLevelAnnotation.Cherry.class.getCanonicalName();
    private static String DEWBERRY_CLASSNAME = ClassLevelAnnotation.Dewberry.class.getCanonicalName();

	private ProcessingEnvironment env;

	private BuilderModelProducer underTest;

	@Before
	public void setup() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		TypeMUtils typeMUtils = new TypeMUtils();
		underTest = new BuilderModelProducer(env, typeMUtils);
	}

	@Test
	public void testSingleDefaultConstructor() {
		// Given:
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(APPLE_CLASSNAME);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("builder classname", "AppleBuilder", builder.getType().getSimpleName());
		assertEquals("size of properties", 0, builder.getProperties().size());
	}

    @Test
    public void testSingleAnnotatedConstructorWithRenaming() {
        // Given:
        TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(BANANA_CLASSNAME);
        System.out.println( pojoTypeElement.getQualifiedName());
        // When:
        Output output = underTest.produce(new Input(pojoTypeElement));
        BuilderM builder = output.getBuilder();

        // Then:
        assertEquals("builder classname", "BananaBuilder", builder.getType().getSimpleName());
        assertEquals("size of properties", 1, builder.getProperties().size());
        assertThat(builder.getProperties(), containsPropertyWithName("colour"));
    }

    @Test
    public void testDefaultConstructorWhereOtherChoicesExist() {
        // Given:
        TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(CHERRY_CLASSNAME);

        // When:
        Output output = underTest.produce(new Input(pojoTypeElement));
        BuilderM builder = output.getBuilder();

        // Then:
        assertEquals("builder classname", "CherryBuilder", builder.getType().getSimpleName());
        assertEquals("size of properties", 1, builder.getProperties().size());
        assertThat(builder.getProperties(), containsPropertyWithName("colour"));
    }

    @Test
    public void testNonDefaultConstructorWhereOtherChoicesExist() {
        // Given:
        TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(DEWBERRY_CLASSNAME);

        // When:
        Output output = underTest.produce(new Input(pojoTypeElement));
        BuilderM builder = output.getBuilder();

        // Then:
        assertEquals("builder classname", "DewberryBuilder", builder.getType().getSimpleName());
        assertEquals("size of properties", 1, builder.getProperties().size());
        assertThat(builder.getProperties(), containsPropertyWithName("colour"));
    }

}
