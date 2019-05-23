package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactToGroupAdditionTest extends TestBase {
  @BeforeClass
  public void ensureGeneralPreConditions() {
    if (app.db().groups().size() == 0 && app.db().contacts().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("new group"));
      Groups groups = app.db().groups();
      app.contact().create(new ContactData()
              .withFirstName("Petr").withMiddleName("Jan").withLastName("Mares")
              .withNickName("honzamares").withTitle("PHDr").inGroup(groups.iterator().next())
              .withCompany("Skoda").withAddress("Prague").withEmail("honzamares@test.com"), true);
    } else if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("formWasEmpty"));
      Groups groups = app.db().groups();
      app.contact().create(new ContactData()
              .withFirstName("Petr").withMiddleName("Jan").withLastName("Mares")
              .withNickName("honzamares").withTitle("PHDr").inGroup(groups.iterator().next())
              .withCompany("Skoda").withAddress("Prague").withEmail("honzamares@test.com"), true);
    } else if (app.db().contacts().size() == 0) {
      Groups groups = app.db().groups();
      app.contact().create(new ContactData()
              .withFirstName("Petr").withMiddleName("Jan").withLastName("Mares")
              .withNickName("honzamares").withTitle("PHDr").inGroup(groups.iterator().next())
              .withCompany("Skoda").withAddress("Prague").withEmail("honzamares@test.com"), true);
    }
  }

  @BeforeMethod
  public void ensureMethodPreConditions(){
    Groups groups = app.db().groups();
    Contacts beforeContacts = app.db().contacts().stream().filter((s) -> s.getGroups().size() < groups.size()).collect(Collectors.toCollection(Contacts::new));
    if (beforeContacts.size() == 0){
      app.contact().create(new ContactData()
              .withFirstName("Petr").withMiddleName("Jan").withLastName("Mares")
              .withNickName("honzamares").withTitle("PHDr")
              //.inGroup(groups.iterator().next())
              .withCompany("Skoda").withAddress("Prague").withEmail("honzamares@test.com"), true);
    }
    if (groups.size() == 1){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("1 groups was here"));
    }
  }

  @Test
  public void testContactToGroupAddition() {
    Groups groups = app.db().groups();
    Contacts beforeContacts = app.db().contacts().stream().filter((s) -> s.getGroups().size() < groups.size()).collect(Collectors.toCollection(Contacts::new));
    ContactData contact = beforeContacts.iterator().next();
    Groups contactGroupsBefore = contact.getGroups();
    groups.removeAll(contactGroupsBefore);
    app.goTo().goToHomePage();
    app.contact().addContactToGroup(groups, contact);
    Contacts afterContacts = app.db().contacts().stream().filter((s) -> s.getId() == contact.getId()).collect(Collectors.toCollection(Contacts::new));


    assertThat(String.valueOf(beforeContacts.equals(afterContacts)), true);
    System.out.println(beforeContacts);
    System.out.println(afterContacts);
  }

}
