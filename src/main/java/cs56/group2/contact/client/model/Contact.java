package cs56.group2.contact.client.model;

public interface Contact {
    String firstName = null;
    String lastName = null;
    String middleName = null;
    String email = null;
    String cell = null;

    public String getFirstName();
    public void setFirstName(String firstName);
    public String getLastName();
    public void setLastName(String lastName);
    public String getMiddleName();
    public void setMiddleName(String middleName);
    public String getEmail();
    public void setEmail(String email);
    public String getCell();
    public void setCell(String cell);
}
