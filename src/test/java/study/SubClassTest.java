package study;

import net.karneim.pojobuilder.TestBase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testenv.ProcessingEnvironmentRunner;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import java.util.List;

import static net.karneim.pojobuilder.matchers.PBMatchers.containsOnly;
import static net.karneim.pojobuilder.matchers.PBMatchers.elementWithName;
import static org.junit.Assert.assertThat;

@RunWith(ProcessingEnvironmentRunner.class)
public class SubClassTest extends TestBase {
    public static class SuperClass {
        @SuppressWarnings("unused")
        private String fieldA;
        public String fieldB;
        public MyData getData() {
            return null;
        }
    }

    public static class SampleClass extends SuperClass {
        @SuppressWarnings("unused")
        private String fieldC;
    }
    public static class MyData {
        
    }

    private Elements elements;
    private Types types;

    @Before
    public void setupEnv() {
        ProcessingEnvironment env = ProcessingEnvironmentRunner.getProcessingEnvironment();
        elements = env.getElementUtils();
        types = env.getTypeUtils();
    }

    @Test
    public void testGetAllMembersOnElementsShouldReturnNoPrivateSuperMembers() {
        // Given:
        Class<?> aClass = SampleClass.class;

        // When:
        // getAllMembers returns the members as they are visible from
        // inside the given class
        TypeElement el = elements.getTypeElement(aClass.getCanonicalName());
        List<? extends Element> members = elements.getAllMembers(el);
        List<VariableElement> fields = ElementFilter.fieldsIn(members);

        // Then:
        assertThat(fields, containsOnly(
                elementWithName("fieldB"),
                elementWithName("fieldC")));
    }
    
    @Test
    public void testXXX() {
        // Given:
        Class<?> aClass = SampleClass.class;
        TypeElement typeEl = elements.getTypeElement(aClass.getCanonicalName());
        DeclaredType type = (DeclaredType)typeEl.asType();
        
        ExecutableElement methodEl = getMethod(typeEl, "getData");
        // When:        
        
        TypeMirror xx = types.asMemberOf(type, methodEl);
        
        // Then:
        
    }

    private ExecutableElement getMethod(TypeElement typeEl, String methodName) {
        List<? extends Element> memberEls = elements.getAllMembers(typeEl);
        List<ExecutableElement> methodsEls = ElementFilter.methodsIn(memberEls);
        for( ExecutableElement el: methodsEls) {
            if ( methodName.equals( el.getSimpleName().toString())) {
                return el;
            }
        }
        throw new IllegalArgumentException(String.format("Method %s not found in %s.", methodName, typeEl));
    }

}
