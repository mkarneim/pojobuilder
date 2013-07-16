package net.karneim.pojobuilder.baseclass;

import net.karneim.pojobuilder.model.TypeM;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.lang.model.element.TypeElement;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class WithBaseClassTest {

    private TypeM baseClassType;

    @Before
    public void setup() {
        baseClassType = mock(TypeM.class);
    }

    @Test
    public void returnsCorrectType() {
         // Given:
        WithBaseClass baseClassStrategy = new WithBaseClass(baseClassType);
        // When:
        TypeM returnedType = baseClassStrategy.getBaseClass();
        // Then:
        assertThat( returnedType, is(sameInstance(baseClassType)));
    }

}
