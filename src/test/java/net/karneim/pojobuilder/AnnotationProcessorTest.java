package net.karneim.pojobuilder;

import net.karneim.pojobuilder.AnnotationProcessor;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import testenv.JavaProject;
import testenv.Util;


public class AnnotationProcessorTest {

	JavaProject prj = new JavaProject(Util.createTempDir());

	@Before
	public void setupFixture() {
		// Enable the GeneratePojoBuilderAnnotationProcessor
		prj.getProcessorClasses().add(
				AnnotationProcessor.class);
		// Enable Logging
		AnnotationProcessor.enableLogging();
	}

	@After
	public void tearDown() {
		prj.delete();
	}

	@Test
	public void shouldCompileEmptyPojo() throws Exception {
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
	public void shouldProduceSimpleAnnotatedPojoBuilder() throws Exception {
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
	
	@Test
	public void shouldProduceContactBuilder() throws Exception {
		// Given there is an annotated pojo source file
		prj.addSourceFile("src/test/data/testdata/Contact.java");

		// When the compilation is invoked
		boolean success = prj.compile();

		// Then a new builder class is generated with a matching name
		Assert.assertTrue("success", success);
		Class<?> newClass = prj
				.findClass("testdata.ContactBuilder");
		Assert.assertNotNull("newClass", newClass);
	}
	
	@Test
	public void shouldProduceContactBuilderIntoTargetPackage() throws Exception {
		// Given there is an annotated pojo source file
		prj.addSourceFile("src/test/data/testdata/intoPackage/Contact.java");

		// When the compilation is invoked
		boolean success = prj.compile();

		// Then a new builder class is generated with a matching name
		Assert.assertTrue("success", success);
		Class<?> newClass = prj
				.findClass("testdata.intoPackage.target.ContactBuilder");
		Assert.assertNotNull("newClass", newClass);
	}
	
	@Test
	public void shouldProduceAbstractBuilderAndSubclass() throws Exception {
		// Given there is an annotated pojo source file
		prj.addSourceFile("src/test/data/testdata/generationgap/Contact.java");

		// When the compilation is invoked
		boolean success = prj.compile();

		// Then a new builder class is generated with a matching name
		Assert.assertTrue("success", success);
		Class<?> newClass = prj
				.findClass("testdata.generationgap.ContactBuilder");
		Assert.assertNotNull("newClass", newClass);
		Class<?> newAbstractClass = prj
				.findClass("testdata.generationgap.AbstractContactBuilder");
		Assert.assertNotNull("newAbstractClass", newAbstractClass);

	}
	

}
