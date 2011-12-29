package samples.with.factory;

public class Contact {
<<<<<<< HEAD
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
=======
    private final String firstname;
    private final String lastname;
    private String email;

    public Contact(String firstname, String lastname) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
>>>>>>> 1fc315b61a33b0dabfe0035a1baa79c1ad3f0c68
