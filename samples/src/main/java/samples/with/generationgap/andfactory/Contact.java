package samples.with.generationgap.andfactory;

import java.util.Arrays;
import java.util.List;

public class Contact {
    private String name;
    private List<String> emailAddresses;

    public List<String> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(List<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public void setName(String name) {
		this.name = name;
	}

	public String getName() {
        return name;
    }

    public String toString() {
        return "Contact: name=" + name + ", emailAddresses=" + (emailAddresses==null?null:Arrays.toString(emailAddresses.toArray()));
    }
}
