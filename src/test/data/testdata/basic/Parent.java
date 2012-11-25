package testdata.basic;

import java.beans.ConstructorProperties;
import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class Parent<K> {
    public String name;
    public String email;
    
    public void setValue(K k) {
    	
    }
    
    public void setStrings(List<String> strings) {
    
    }

}
