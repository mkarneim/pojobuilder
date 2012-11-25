package testdata;

import java.beans.ConstructorProperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Contact {
    public String name;
    public String email;

}
