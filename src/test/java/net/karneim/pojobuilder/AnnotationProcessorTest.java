package net.karneim.pojobuilder;

import java.lang.reflect.Modifier;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import testenv.JavaProject;
import testenv.Util;

/**
 * The {@link AnnotationProcessorTest} is a simple acceptance test for running
 * the {@link AnnotationProcessor} on pojos.
 * 
 */
public class AnnotationProcessorTest {

	JavaProject prj = new JavaProject(Util.createTempDir());

	@Before
	public void setupJavaProject() {
		// Enable the AnnotationProcessor
		prj.getProcessorClasses().add(AnnotationProcessor.class);
		// Enable Logging
		AnnotationProcessor.enableLogging();
	}

	@After
	public void tearDownJavaProject() {
		prj.delete();
	}

	@Test
	public void testCompileShouldCompileEmptyPojo() throws Exception {
		// Given: there is a pojo source file
		prj.addSourceFile("src/test/data/testdata/EmptyPojo.java");

		// When: the compilation is invoked
		boolean success = prj.compile();

		// Then: the class file is generated
		Assert.assertEquals("success", true, success);
		Class<?> pojoClass = prj.findClass("testdata.EmptyPojo");
		Assert.assertNotNull("pojoClass", pojoClass);
	}

	@Test
	public void testCompileShouldGeneratePojoBuilder1() throws Exception {
		// Given: there is an annotated pojo source file
		prj.addSourceFile("src/test/data/testdata/SimpleAnnotatedPojo.java");

		// When: the compilation is invoked
		boolean success = prj.compile();

		// Then: a new builder class is generated with a matching name
		Assert.assertEquals("success", true, success);
		Class<?> newClass = prj.findClass("testdata.SimpleAnnotatedPojoBuilder");
		Assert.assertNotNull("newClass", newClass);
	}

	@Test
	public void testCompileShouldGeneratePojoBuilder2() throws Exception {
		// Given: there is an annotated pojo source file
		prj.addSourceFile("src/test/data/testdata/Contact.java");

		// When: the compilation is invoked
		boolean success = prj.compile();

		// Then: a new builder class is generated with a matching name
		Assert.assertEquals("success", true, success);
		Class<?> newClass = prj.findClass("testdata.ContactBuilder");
		Assert.assertNotNull("newClass", newClass);
	}

	@Test
	public void testCompileShouldGeneratePojoBuilderIntoTargetPackage() throws Exception {
		// Given: there is an annotated pojo source file with "intoPackage" directive
		prj.addSourceFile("src/test/data/testdata/intoPackage/Contact.java");

		// When: the compilation is invoked
		boolean success = prj.compile();

		// Then: a new builder class is generated into target directory
		Assert.assertEquals("success", true, success);
		Class<?> newClass = prj.findClass("testdata.intoPackage.target.ContactBuilder");
		Assert.assertNotNull("newClass", newClass);
	}

	@Test
	public void testCompileShouldGeneratePojoBuilderWithGenerationGap() throws Exception {
		// Given: there is an annotated pojo source file with "useGenerationGap" directive
		prj.addSourceFile("src/test/data/testdata/generationgap/Contact.java");

		// When: the compilation is invoked
		boolean success = prj.compile();

		// Then: two new classes are generated, one abstract and the other concrete
		Assert.assertEquals("success", true, success);
		Class<?> newClass = prj.findClass("testdata.generationgap.ContactBuilder");
		Assert.assertNotNull("newClass", newClass);
		Class<?> newAbstractClass = prj.findClass("testdata.generationgap.AbstractContactBuilder");
		Assert.assertNotNull("newAbstractClass", newAbstractClass);
		Assert.assertEquals( "abstract",  true, Modifier.isAbstract(newAbstractClass.getModifiers()));
		Assert.assertEquals( "abstract",  false, Modifier.isAbstract(newClass.getModifiers()));
		Assert.assertEquals( "superclass",  newAbstractClass, newClass.getSuperclass());
	}

}
