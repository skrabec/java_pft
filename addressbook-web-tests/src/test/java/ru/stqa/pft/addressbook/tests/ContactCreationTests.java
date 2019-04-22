package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().createContact(new ContactData("Petr", "Jan", "Mares", "[none]", "petrmares", "PHDr", "Skoda", "Prague", "petrmares@test.com"), true);
  }
}