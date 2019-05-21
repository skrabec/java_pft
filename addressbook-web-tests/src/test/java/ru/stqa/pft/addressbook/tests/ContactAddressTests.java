package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {



  @BeforeMethod
  public void ensurePreConditions() {
    Groups groups = app.db().groups();
    if (app.db().groups().size()==0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("formWasEmpty"));
    } else if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("Petr").withMiddleName("Jan").withLastName("Mares")
              .withNickName("honzamares").withTitle("PHDr").inGroup(groups.iterator().next())
              .withCompany("Skoda").withAddress("Prague").withEmail("honzamares@test.com"), true);
      //.withPhoto(photo), true);
    }
  }

  @Test
  public void testContactAddress(){
    app.goTo().goToHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
  }

  private String mergeAddress(ContactData contact) {
    return Arrays.asList(contact.getAddress()).stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }
}
