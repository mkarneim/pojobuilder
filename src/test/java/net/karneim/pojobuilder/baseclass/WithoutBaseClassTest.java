package net.karneim.pojobuilder.baseclass;

import net.karneim.pojobuilder.model.TypeM;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class WithoutBaseClassTest {

    @Test
    public void returnsNull() {
         // Given:
        WithoutBaseClass baseClassStrategy = new WithoutBaseClass();
        // When:
        TypeM baseClassType = baseClassStrategy.getBaseClass();
        // Then:
        assertThat( baseClassType, is(nullValue()));
    }

}
