package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification (){
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Petr", "Jan", "Mares",  "[none]","petrmares", "PHDr", "Skoda", "Prague", "petrmares@test.com"), true);
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("test", "test", "test", null,"test", "test", "test", "test", "test@test.com"), false);
    app.getContactHelper().submitContactModification();


  }
}
