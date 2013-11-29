package net.karneim.pojobuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import testenv.JavaProject;
import testenv.Util;

import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The {@link AnnotationProcessorTest} is a simple acceptance test for running
 * the {@link AnnotationProcessor} on pojos.
 * 
 */
public class AnnotationProcessorTest {

	private JavaProject prj = new JavaProject(Util.createTempDir());

	@Before
	public void setupJavaProject() {
		// Enable the AnnotationProcessor
		prj.getProcessorClasses().add(AnnotationProcessor.class);
	}

	@After
	public void tearDownJavaProject() {
		prj.delete();
	}

	@Test
	public void testCompileShouldCompileEmptyPojo() throws Exception {
		// Given: there is a pojo source file
		prj.addSourceFile("src/test/java/testdata/EmptyPojo.java");

		// When: the compilation is invoked
		boolean success = prj.compile();

		// Then: the class file is generated
		assertEquals("success", true, success);
		Class<?> pojoClass = prj.findClass("testdata.EmptyPojo");
		assertNotNull("pojoClass", pojoClass);
	}

	@Test
	public void testCompileShouldGeneratePojoBuilder1() throws Exception {
		// Given: there is an annotated pojo source file
		prj.addSourceFile("src/test/java/testdata/SimpleAnnotatedPojo.java");

		// When: the compilation is invoked
		boolean success = prj.compile();

		// Then: a new builder class is generated with a matching name
		assertEquals("success", true, success);
		Class<?> newClass = prj.findClass("testdata.SimpleAnnotatedPojoBuilder");
		assertNotNull("newClass", newClass);
	}

	@Test
	public void testCompileShouldGeneratePojoBuilder2() throws Exception {
		// Given: there is an annotated pojo source file
		prj.addSourceFile("src/test/java/testdata/Contact.java");

		// When: the compilation is invoked
		boolean success = prj.compile();

		// Then: a new builder class is generated with a matching name
		assertEquals("success", true, success);
		Class<?> newClass = prj.findClass("testdata.ContactBuilder");
		assertNotNull("newClass", newClass);
	}

	@Test
	public void testCompileShouldGeneratePojoBuilderIntoTargetPackage() throws Exception {
		// Given: there is an annotated pojo source file with "intoPackage"
		// directive
		prj.addSourceFile("src/test/java/testdata/intoPackage/Contact.java");

		// When: the compilation is invoked
		boolean success = prj.compile();

		// Then: a new builder class is generated into target directory
		assertEquals("success", true, success);
		Class<?> newClass = prj.findClass("testdata.intoPackage.target.ContactBuilder");
		assertNotNull("newClass", newClass);
	}

	@Test
	public void testCompileShouldGeneratePojoBuilderWithGenerationGap() throws Exception {
		// Given: there is an annotated pojo source file with "useGenerationGap"
		// directive
		prj.addSourceFile("src/test/java/testdata/generationgap/Contact.java");

		// When: the compilation is invoked
		boolean success = prj.compile();

		// Then: two new classes are generated, one abstract and the other
		// concrete
		assertEquals("success", true, success);
		Class<?> newClass = prj.findClass("testdata.generationgap.ContactBuilder");
		assertNotNull("newClass", newClass);
		Class<?> newAbstractClass = prj.findClass("testdata.generationgap.AbstractContactBuilder");
		assertNotNull("newAbstractClass", newAbstractClass);
		assertEquals("abstract", true, Modifier.isAbstract(newAbstractClass.getModifiers()));
		assertEquals("abstract", false, Modifier.isAbstract(newClass.getModifiers()));
		assertEquals("superclass", newAbstractClass.getCanonicalName(), newClass.getSuperclass()
                .getCanonicalName());
	}

}
