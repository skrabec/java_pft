package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreCondition() {
    if (app.db().groups().size()==0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("formWasEmpty"));
    }
  }


  @DataProvider
  public Iterator<Object[]> validContactsJSON() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {}
    .getType()); //List<ContactData>.class
    return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validContactsJSON", enabled = false)
  public void testContactCreationJSON(ContactData contact) {
    Contacts before = app.db().contacts();
    //ContactData contact = new ContactData()
    //        .withFirstName("Matej").withLastName("Mares").withGroup("[none]").withPhoto(photo);
    app.contact().create(contact, contact.withPhoto(photo), true);
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    verifyContactListInUI();
  }

  @Test
  public void testContactCreation(){
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    ContactData contact = new ContactData()
            .withFirstName("firstname")
            .withMiddleName("middlename")
            .withLastName("lastname")
            .withNickName("nickname")
            .withCompany("company")
            .withTitle("title")
            .withAddress("address")
            .withHomePhone("home")
            .withMobile("mobile")
            .withWorkPhone("home")
            .withEmail("email")
            .withEmail2("email2")
            .withEmail3("email3")
            .inGroup(groups.iterator().next())
            .withPhoto(photo);
    app.contact().create(contact, contact.withPhoto(photo), true);
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    verifyContactListInUI();
  }

}