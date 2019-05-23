package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

public class ContactFromGroupDeletionTest extends TestBase {

  @BeforeClass
  public void ensureGeneralPreConditions() {
    if (app.db().contacts().size() == 0 && app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("new group"));
      Groups groups = app.db().groups();
      app.contact().create(new ContactData()
              .withFirstName("Petr").withMiddleName("Jan").withLastName("Mares")
              .withNickName("honzamares").withTitle("PHDr").inGroup(groups.iterator().next())
              .withCompany("Skoda").withAddress("Prague").withEmail("honzamares@test.com"), true);
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("formWasEmpty"));
    } else if (app.db().contacts().size() == 0) {
      Groups groups = app.db().groups();
      app.contact().create(new ContactData()
              .withFirstName("Petr").withMiddleName("Jan").withLastName("Mares")
              .withNickName("honzamares").withTitle("PHDr").inGroup(groups.iterator().next())
              .withCompany("Skoda").withAddress("Prague").withEmail("honzamares@test.com"), true);
    }
  }


  @Test
  public void testContactFromGroupDeletion() {
    Groups groups = app.db().groups();
    Contacts beforeContacts = app.db().contacts().stream().filter((s) -> s.getGroups().size() > 0).collect(Collectors.toCollection(Contacts::new));
    if (beforeContacts.size() > 0) {
      ContactData contact = beforeContacts.iterator().next();
      Groups contactGroupsBefore = contact.getGroups();
      groups.removeAll(contactGroupsBefore);
      app.contact().deleteFromGroup(contact, contactGroupsBefore);
    } else if (beforeContacts.size() == 0) {
      ContactToGroupAdditionTest addToGroup = new ContactToGroupAdditionTest();
      addToGroup.testContactToGroupAddition();
      app.goTo().goToHomePage();
      Contacts contact = app.db().contacts().stream().filter((s) -> s.getGroups().size() > 0).collect(Collectors.toCollection(Contacts::new));
      ContactData contactData = contact.iterator().next();
      Groups contactGroupsBefore = contactData.getGroups();
      groups.removeAll(contactGroupsBefore);
      app.contact().deleteFromGroup(contactData, contactGroupsBefore);
    }

    //Contacts afterContacts = app.db().contacts().stream().filter((s) -> s.getId() == contact.getId()).collect(Collectors.toCollection(Contacts::new));

    //assertThat(String.valueOf(beforeContacts.equals(afterContacts)), true);
    System.out.println(beforeContacts);
    //System.out.println(afterContacts);
  }
}