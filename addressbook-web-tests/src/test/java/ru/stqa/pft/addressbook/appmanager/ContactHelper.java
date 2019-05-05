package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNickName());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//*[@id=\"content\"]/form[2]/div[2]/input"));
  }

  public void closePopUp(){
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//tr[@name='entry']//td[8]")).get(index).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void create(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm(contact, creation);
    submitContactCreation();
  }

  public void modify(int index, ContactData contact) {
    initContactModification(index);
    fillContactForm(contact, false);
    submitContactModification();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    closePopUp();
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> list() {
    List <ContactData> contacts = new ArrayList<>();
    List <WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement cell : rows){
      List<WebElement> cells = cell.findElements(By.tagName("td"));
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      int id = Integer.parseInt(cell.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
    }
    return contacts;
  }

  public Set<ContactData> all() {
    Set <ContactData> contacts = new HashSet<ContactData>();
    List <WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement cell : rows){
      List<WebElement> cells = cell.findElements(By.tagName("td"));
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      int id = Integer.parseInt(cell.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
    }
    return contacts;
  }
}
