package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreConditions() throws InterruptedException {
    if (app.db().groups().size()==0 && app.db().contacts().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("formWasEmpty"));
      Groups groups = app.db().groups();
      app.contact().create(new ContactData()
              .withFirstName("Petr").withMiddleName("Jan").withLastName("Mares")
              .withNickName("honzamares").withTitle("PHDr").inGroup(groups.iterator().next())
              .withCompany("Skoda").withAddress("Prague").withEmail("honzamares@test.com"), true);
    } else if (app.db().groups().size()==0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("formWasEmpty"));
      Groups groups = app.db().groups();
      app.contact().create(new ContactData()
              .withFirstName("Petr").withMiddleName("Jan").withLastName("Mares")
              .withNickName("honzamares").withTitle("PHDr").inGroup(groups.iterator().next())
              .withCompany("Skoda").withAddress("Prague").withEmail("honzamares@test.com"), true);
    } else if (app.db().contacts().size() == 0){
      Groups groups = app.db().groups();
      app.contact().create(new ContactData()
              .withFirstName("Petr").withMiddleName("Jan").withLastName("Mares")
              .withNickName("honzamares").withTitle("PHDr").inGroup(groups.iterator().next())
              .withCompany("Skoda").withAddress("Prague").withEmail("honzamares@test.com"), true);
    }
  }

  @Test(enabled = false)
  public void testContactModification() {
    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();
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
            .withPhoto(photo)
            .inGroup(groups.iterator().next());
    app.goTo().goToHomePage();
    app.contact().modify(contact);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    assertEquals(before, after);
    assertThat(after,
            equalTo(before.withModified(modifiedContact, contact)));

    verifyContactListInUI();
  }

}
