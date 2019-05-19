package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreConditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("Petr").withMiddleName("Jan").withLastName("Mares")
              .withGroup("[none]").withNickName("honzamares").withTitle("PHDr")
              .withCompany("Skoda").withAddress("Prague").withEmail("honzamares@test.com").withPhoto(photo), true);
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().goToHomePage();
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size() - 1);

    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
