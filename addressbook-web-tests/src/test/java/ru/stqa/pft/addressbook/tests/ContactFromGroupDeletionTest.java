package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

public class ContactFromGroupDeletionTest extends TestBase {

  @Test
  public void testContactFromGroupDeletion() {
    Groups groups = app.db().groups();
    Contacts beforeContacts = app.db().contacts().stream().filter((s) -> s.getGroups() != null).collect(Collectors.toCollection(Contacts::new));
    if (beforeContacts.size() > 0) {
      ContactData contact = beforeContacts.iterator().next();
      Groups contactGroupsBefore = contact.getGroups();
      groups.removeAll(contactGroupsBefore);
      //app.goTo().goToHomePage();
      app.contact().deleteFromGroup(contact, contactGroupsBefore);
      //app.goTo().goToHomePage();
    }
  }
}
