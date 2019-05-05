package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreConditions() {
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("Petr").withMiddleName("Jan").withLastName("Mares")
              .withGroup("[none]").withNickName("honzamares").withTitle("PHDr")
              .withCompany("Skoda").withAddress("Prague").withEmail("honzamares@test.com"), true);
    }
  }

  @Test(enabled = false)
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("test1").withLastName("test2");
    //app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
