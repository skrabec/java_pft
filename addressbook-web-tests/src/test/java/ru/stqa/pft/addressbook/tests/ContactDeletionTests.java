package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreConditions() {
    if (!app.contact().isThereAContact()) {
      app.contact().createContact(new ContactData("Petr", "Jan", "Mares", "[none]", "petrmares", "PHDr", "Skoda", "Prague", "petrmares@test.com"), true);
    }
  }

  @Test
  public void testContactDeletion() {
    List<ContactData> before = app.contact().list();
    app.contact().selectContact(before.size() - 1);
    app.contact().deleteSelectedContacts();
    app.contact().closePopUp();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
