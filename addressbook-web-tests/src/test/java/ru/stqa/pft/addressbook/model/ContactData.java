package ru.stqa.pft.addressbook.model;

public class ContactData {

  private int id;
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private String group;
  private final String nickName;
  private final String title;
  private final String company;
  private final String address;
  private final String email;

    public ContactData(String firstName, String middleName, String lastName, String group, String nickName, String title, String company, String address, String email) {
    this.id = Integer.MAX_VALUE;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.group = group;
    this.nickName = nickName;
    this.title = title;
    this.company = company;
    this.address = address;
    this.email = email;
  }

  public ContactData(int id, String firstName, String middleName, String lastName, String group, String nickName, String title, String company, String address, String email) {
    this.id = id;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.group = group;
    this.nickName = nickName;
    this.title = title;
    this.company = company;
    this.address = address;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickName() {
    return nickName;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            " id " + id + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

}
