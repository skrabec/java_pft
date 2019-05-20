package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreConditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("Petr").withMiddleName("Jan").withLastName("Mares")
              .withGroup("[none]").withNickName("honzamares").withTitle("PHDr")
              .withCompany("Skoda").withAddress("Prague").withEmail("honzamares@test.com"), true);
              //.withPhoto(photo), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("test1")
            .withMiddleName("md")
            .withLastName("test2")
            .withNickName("nn")
            .withTitle("title")
            .withCompany("comp")
            .withAddress("address")
            .withEmail("email")
            .withEmail2("email2")
            .withEmail3("email3")
            .withHomePhone("home")
            .withMobile("mob")
            .withWorkPhone("work")
            .withPhoto(photo);
    app.goTo().goToHomePage();
    app.contact().modify(contact);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    assertEquals(before, after);
    assertThat(after,
            equalTo(before.withModified(modifiedContact, contact)));
  }
}
