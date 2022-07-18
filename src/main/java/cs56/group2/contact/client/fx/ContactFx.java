package cs56.group2.contact.client.fx;

import cs56.group2.contact.client.model.Contact;
import javafx.beans.property.SimpleStringProperty;

public class ContactFx {

    private int id = -1;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty middleName;
    private SimpleStringProperty email;
    private SimpleStringProperty cell;

    public ContactFx() {
        firstName = new SimpleStringProperty();
        lastName = new SimpleStringProperty();
        middleName = new SimpleStringProperty();
        email = new SimpleStringProperty();
        cell = new SimpleStringProperty();
    }

    public ContactFx(Contact contact) {
        this();

        this.id = contact.getId();
        this.firstName.set(contact.getFirstName());
        this.middleName.set(contact.getMiddleName());
        this.lastName.set(contact.getLastName());
        this.cell.set(contact.getCell());
        this.email.set(contact.getEmail());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public SimpleStringProperty middleNameProperty() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getCell() {
        return cell.get();
    }

    public SimpleStringProperty cellProperty() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell.set(cell);
    }

    public Contact getContact() {
        Contact contact = new Contact();
        contact.setId(id);
        contact.setFirstName(firstName.get());
        contact.setMiddleName(middleName.get());
        contact.setLastName(lastName.get());
        contact.setEmail(email.get());
        contact.setCell(cell.get());

        return contact;
    }

    @Override
    public String toString() {
        return "ContactFx{" +
            "id=" + id +
            ", firstName=" + firstName +
            ", lastName=" + lastName +
            ", middleName=" + middleName +
            ", email=" + email +
            ", cell=" + cell +
            '}';
    }
}
