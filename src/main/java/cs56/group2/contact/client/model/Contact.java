package cs56.group2.contact.client.model;

public interface Contact {
    int getId();
    void setId(int id);
    String getFirstName();
    void setFirstName(String firstName);
    String getLastName();
    void setLastName(String lastName);
    String getMiddleName();
    void setMiddleName(String middleName);
    String getEmail();
    void setEmail(String email);
    String getCell();void setCell(String cell);
}
