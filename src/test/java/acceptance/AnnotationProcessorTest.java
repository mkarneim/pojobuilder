package acceptance;

import net.karneim.pojobuilder.GeneratePojoBuilderAnnotationProcessor;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import acceptance.env.JavaProject;
import acceptance.env.Util;

public class AnnotationProcessorTest {

	JavaProject prj = new JavaProject(Util.createTempDir());

	@Before
	public void setupFixture() {
		// Enable the GeneratePojoBuilderAnnotationProcessor
		prj.getProcessorClasses().add(
				GeneratePojoBuilderAnnotationProcessor.class);
		// Enable Logging
		GeneratePojoBuilderAnnotationProcessor.enableLogging();
	}

	@After
	public void tearDown() {
		prj.delete();
	}

	@Test
	public void compilationOfJavaSourceProducesClassFile() throws Exception {
		// Given there is a pojo source file
		prj.addSourceFile("src/test/data/testdata/EmptyPojo.java");

		// When the compilation is invoked
		boolean success = prj.compile();

		// Then the class file is generated
		Assert.assertTrue("success", success);
		Class<?> pojoClass = prj.findClass("testdata.EmptyPojo");
		Assert.assertNotNull("pojoClass", pojoClass);
	}

	@Test
	public void processorCreatesClassForAnnotatedPojo() throws Exception {
		// Given there is an annotated pojo source file
		prj.addSourceFile("src/test/data/testdata/SimpleAnnotatedPojo.java");

		// When the compilation is invoked
		boolean success = prj.compile();

		// Then a new builder class is generated with a matching name
		Assert.assertTrue("success", success);
		Class<?> newClass = prj
				.findClass("testdata.SimpleAnnotatedPojoBuilder");
		Assert.assertNotNull("newClass", newClass);
	}

}
