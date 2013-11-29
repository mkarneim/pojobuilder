package testdata.intoPackage;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import java.beans.ConstructorProperties;

@GeneratePojoBuilder(intoPackage = "*.target")
public class Contact {
    private final String name;
    private String email;

    @ConstructorProperties({ "name" })
    public Contact(String aName) {
        this.name = aName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Contact: name=" + name + ", email=" + email;
    }
}
