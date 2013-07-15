package net.karneim.pojobuilder.packages;

import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;
import net.karneim.pojobuilder.BuildException;
import net.karneim.pojobuilder.GeneratePojoBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(ZohhakRunner.class)
public class ParameterisablePackageStrategyTest {

    private ProcessingEnvironment env;
    private ParameterisablePackageStrategy packageStrategy;
    private GeneratePojoBuilder annotation;
    private TypeElement pojoType;
    private PackageElement packageElement;

    @Before
    public void setUp() {
        env = mock(ProcessingEnvironment.class, Mockito.RETURNS_DEEP_STUBS);
        packageStrategy = new ParameterisablePackageStrategy(env);
        annotation = mock(GeneratePojoBuilder.class);
        pojoType = mock(TypeElement.class, Mockito.RETURNS_DEEP_STUBS);
        packageElement = mock(PackageElement.class, Mockito.RETURNS_DEEP_STUBS);
        when(env.getElementUtils().getPackageOf(pojoType)).thenReturn(packageElement);
    }

    @Test
    @Ignore
    public void defaultAnnotation() {
        // Given:
        // TODO need a real ProcessingEnvironment to do this test without repeating the default
        when(packageElement.getQualifiedName().toString()).thenReturn("a.b.c");
        when(annotation.intoPackage()).thenReturn("*");
        // When:
        String name = packageStrategy.getPackage(annotation, pojoType);
        // Then:
        assertEquals("a.b.c", name);
    }

    @Test
    public void fixedPackage() {
        // Given:
        when(packageElement.getQualifiedName().toString()).thenReturn("a.b.c");
        when(annotation.intoPackage()).thenReturn("d.e.f");
        // When:
        String name = packageStrategy.getPackage(annotation, pojoType);
        // Then:
        assertEquals("d.e.f", name);
    }

    @Test
    public void aliasedPackage() {
        // Given:
        when(packageElement.getQualifiedName().toString()).thenReturn("a.b.c");
        when(annotation.intoPackage()).thenReturn("*.d");
        // When:
        String name = packageStrategy.getPackage(annotation, pojoType);
        // Then:
        assertEquals("a.b.c.d", name);
    }

    @TestWith({"", "null"})
    public void fixedDefaultPackage(String defaultPackage) {
        // Given:
        when(packageElement.getQualifiedName().toString()).thenReturn("a.b.c");
        when(annotation.intoPackage()).thenReturn(defaultPackage);
        // When:
        String name = packageStrategy.getPackage(annotation, pojoType);
        // Then:
        assertEquals("", name);
    }

    @Test
    public void aliasedDefaultPackage() {
        // Given:
        when(packageElement.getQualifiedName().toString()).thenReturn("");
        when(annotation.intoPackage()).thenReturn("*.builder");
        // When:
        String name = packageStrategy.getPackage(annotation, pojoType);
        // Then:
        assertEquals("builder", name);
    }

    //@Test(expected=BuildException.class) https://code.google.com/p/zohhak/issues/detail?id=2
    // TODO add more examples
    @TestWith({".", ".p"})
    public void junkPackageFails(String junkName) {
        // Given:
        when(annotation.intoPackage()).thenReturn(junkName);
        // When:
        try {
            String name = packageStrategy.getPackage(annotation, pojoType);
            // Then:
            fail();
        } catch (BuildException e) {
            // NO OP
        }
    }

}
