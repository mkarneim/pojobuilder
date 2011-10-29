package acceptance;

import net.karneim.pojobuilder.GeneratePojoBuilderAnnotationProcessor;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import acceptance.env.Environment;
import acceptance.env.Util;

public class AnnotationProcessorTest {

	Environment env = new Environment(Util.createTempDir());

	@Before
	public void setupFixture() {
		env.getProcessorClasses().add(
				GeneratePojoBuilderAnnotationProcessor.class);
	}

	@After
	public void tearDown() {
		env.destroy();
	}

	@Test
	public void compilationOfJavaSourceProducesClassFile() throws Exception {
		// Given there is a pojo source file
		env.addSourceFile("src/test/data/testdata/EmptyPojo.java");

		// When the compilation is invoked
		boolean success = env.compile();

		// Then the class file is generated
		Assert.assertTrue("success", success);
		Class pojoClass = env.findClass("testdata.EmptyPojo");
		Assert.assertNotNull("pojoClass", pojoClass);
	}

	@Test
	public void processorCreatesClassForAnnotatedPojo() throws Exception {
		// Given there is an annotated pojo source file
		env.addSourceFile("src/test/data/testdata/SimpleAnnotatedPojo.java");

		// When the compilation is invoked
		boolean success = env.compile();

		// Then a new builder class is generated with a matching name
		Assert.assertTrue("success", success);
		Class newClass = env.findClass("testdata.SimpleAnnotatedPojoBuilder");
		Assert.assertNotNull("newClass", newClass);
	}

}
