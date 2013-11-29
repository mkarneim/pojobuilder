package testdata.factory;

public class Contact {

    private final String surname;
    private final String firstname;
    private String email;

    public Contact(String aSurname, String aFirstname) {
        super();
        this.surname = aSurname;
        this.firstname = aFirstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstname() {
        return firstname;
    }

}
