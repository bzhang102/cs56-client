package cs56.group2.contact.client.fx;

import cs56.group2.contact.client.model.SimpleContact;
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

    public ContactFx(SimpleContact simpleContact) {
        this();

        this.id = simpleContact.getId();
        this.firstName.set(simpleContact.getFirstName());
        this.middleName.set(simpleContact.getMiddleName());
        this.lastName.set(simpleContact.getLastName());
        this.cell.set(simpleContact.getCell());
        this.email.set(simpleContact.getEmail());
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

    public SimpleContact getContact() {
        SimpleContact simpleContact = new SimpleContact();
        simpleContact.setId(id);
        simpleContact.setFirstName(firstName.get());
        simpleContact.setMiddleName(middleName.get());
        simpleContact.setLastName(lastName.get());
        simpleContact.setEmail(email.get());
        simpleContact.setCell(cell.get());

        return simpleContact;
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
