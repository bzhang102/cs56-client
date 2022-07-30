package cs56.group2.contact.client.model;

public class SimpleContact implements Contact{

    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String cell;

    public SimpleContact() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getCell() {
        return cell;
    }

    @Override
    public void setCell(String cell) {
        this.cell = cell;
    }

    @Override
    public String toString() {
        return "Contact{" +
            "id=" + id +
            ", firstName=" + firstName +
            ", lastName=" + lastName +
            ", middleName=" + middleName +
            ", email=" + email +
            ", cell=" + cell +
            '}';
    }
}
