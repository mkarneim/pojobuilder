package net.karneim.pojobuilder.name;

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
import javax.lang.model.element.TypeElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(ZohhakRunner.class)
public class ParameterisableNameStrategyTest {

    private ProcessingEnvironment env;
    private ParameterisableNameStrategy nameStrategy;
    private GeneratePojoBuilder annotation;
    private TypeElement pojoType;

    @Before
    public void setUp() {
        env = mock(ProcessingEnvironment.class);
        nameStrategy = new ParameterisableNameStrategy(env);
        annotation = mock(GeneratePojoBuilder.class);
        pojoType = mock(TypeElement.class, Mockito.RETURNS_DEEP_STUBS);
        when(pojoType.getSimpleName().toString()).thenReturn("ClassName");
    }

    @Test
    @Ignore
    public void defaultAnnotation() {
        // Given:
        // TODO need a real ProcessingEnvironment to do this test without repeating the default
        when(annotation.withName()).thenReturn("*Builder");
        // When:
        String name = nameStrategy.getName(annotation, pojoType);
        // Then:
        assertEquals("ClassNameBuilder", name);
    }

    @Test
    public void fixedName() {
        // Given:
        when(annotation.withName()).thenReturn("MyBuilder");
        // When:
        String name = nameStrategy.getName(annotation, pojoType);
        // Then:
        assertEquals("MyBuilder", name);
    }

    @Test
    public void aliasedName() {
        // Given:
        when(annotation.withName()).thenReturn("My*Constructor");
        // When:
        String name = nameStrategy.getName(annotation, pojoType);
        // Then:
        assertEquals("MyClassNameConstructor", name);
    }

    //@Test(expected=BuildException.class) https://code.google.com/p/zohhak/issues/detail?id=2
    @TestWith({"", "  ", "\t", "null"})
    public void emptyNameFails(String emptyName) {
        // Given:
        when(annotation.withName()).thenReturn(emptyName);
        // When:
        try {
            String name = nameStrategy.getName(annotation, pojoType);
            // Then:
            fail();
        } catch (BuildException e) {
            // NO OP
        }
    }

    @Ignore
    //@Test(expected=BuildException.class) https://code.google.com/p/zohhak/issues/detail?id=2
    @TestWith({"A B", "A+B", "A!B"})
    public void junkNameFails(String junkName) {
        // Given:
        when(annotation.withName()).thenReturn(junkName);
        // When:
        try {
            String name = nameStrategy.getName(annotation, pojoType);
            // Then:
            fail();
        } catch (BuildException e) {
            // NO OP
        }
    }

}
