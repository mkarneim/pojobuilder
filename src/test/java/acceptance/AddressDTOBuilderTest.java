package acceptance;

import java.lang.reflect.Constructor;

import org.junit.Assert;
import org.junit.Test;

/**
 * The class <code>AddressDTOBuilderTest</code> contains tests for the class
 * {@link <code>AddressDTOBuilder</code>}
 * 
 * @pattern JUnit Test Case
 * @generatedBy CodePro at 14.10.11 10:42
 * @author KarneimM
 * @version $Revision$
 */
public class AddressDTOBuilderTest {

    private static final String BUILDER_CLASS_NAME = "pojos.AddressDTOBuilder";

    private Class getBuilderClass()
        throws ClassNotFoundException {
        Class builderClass = Class.forName(BUILDER_CLASS_NAME);
        return builderClass;
    }

    @Test
    public void builderClassMustExists()
        throws Exception {
        Class builderClass = getBuilderClass();
        Assert.assertNotNull("builderClass", builderClass);
    }

    @Test
    public void builderClassHasDefaultContructor()
        throws Exception {
        Class builderClass = getBuilderClass();
        Constructor defaultConstructor = builderClass.getConstructor(new Class[0]);
        Assert.assertNotNull("defaultConstructor", defaultConstructor);
    }
    
    

}
