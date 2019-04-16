package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Petr", "Jan", "Mares", "petrmares", "PHDr", "Skoda", "Prague", "petrmares@test.com"));
    app.getContactHelper().submitContactCreation();
  }
}