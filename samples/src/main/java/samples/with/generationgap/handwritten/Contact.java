package samples.with.generationgap.handwritten;

import java.beans.ConstructorProperties;
import java.util.Arrays;
import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withGenerationGap = true)
public class Contact {
    private final String name;
    private List<String> emailAddresses;

    @ConstructorProperties({ "name" })
    public Contact(String aName) {
        this.name = aName;
    }

    public List<String> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(List<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Contact: name=" + name + ", emailAddresses="
                + (emailAddresses == null ? null : Arrays.toString(emailAddresses.toArray()));
    }
}
