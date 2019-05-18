package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "addressbook")
public class ContactData {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "firstName")
  @Expose
  private String firstName;

  @Column(name = "middleName")
  @Expose
  private String middleName;

  @Column(name = "lastName")
  @Expose
  private String lastName;

  @Transient
  @Expose
  private String group;

  @Column(name = "nickName")
  @Expose
  private String nickName;

  @Column(name = "title")
  @Expose
  private String title;

  @Column(name = "company")
  @Expose
  private String company;

  @Column(name = "address")
  @Type(type = "text")
  @Expose
  private String address;

  @Column(name = "email")
  @Type(type = "text")
  @Expose
  private String email;

  @Column(name = "email2")
  @Type(type = "text")
  @Expose
  private String email2;

  @Column(name = "email3")
  @Type(type = "text")
  @Expose
  private String email3;

  @Column(name = "home")
  @Type(type = "text")
  @Expose
  private String homePhone;

  @Column(name = "mobile")
  @Type(type = "text")
  @Expose
  private String mobile;

  @Column(name = "work")
  @Type(type = "text")
  @Expose
  private String workPhone;

  @Transient
  private String allPhones;
  @Transient
  private String allEmails;

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", middleName='" + middleName + '\'' +
            '}';
  }

  @Column(name = "photo")
  @Type(type = "text")
  @Expose
  private String photo;

  @Transient
  @Expose
  private String photoBase64;

  public String getPhotoBase64() {
    return photoBase64;
  }

  public ContactData withPhotoBase64(String photoBase64) {
    this.photoBase64 = photoBase64;
    return this;
  }

  public File getPhoto() {
    return new File(photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getEmail2() {
    return email2;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withNickName(String nickName) {
    this.nickName = nickName;
    return this;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
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

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobile() {
    return mobile;
  }
  public String getWorkPhone() {
    return workPhone;
  }

}
