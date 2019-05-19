package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {

  @Test
  public void testDbConnectionGroups(){
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
      Statement st = conn.createStatement();
      ResultSet resultSet = st.executeQuery("select group_id,group_name,group_header,group_footer  from group_list");
      Groups groups = new Groups();
      while (resultSet.next()){
        groups.add(new GroupData().withId(resultSet.getInt("group_id")).withName(resultSet.getString("group_name"))
                .withHeader(resultSet.getString("group_header")).withFooter(resultSet.getString("group_footer")));
      }
      resultSet.close();
      st.close();
      conn.close();
      System.out.println(groups);


      // Do something with the Connection

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }

  @Test
  public void testDbConnectionContacts(){
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
      Statement st = conn.createStatement();
      ResultSet resultSet = st.executeQuery("select * from addressbook where deprecated = '0000-00-00'");
      Contacts contacts = new Contacts();
      while (resultSet.next()){
        contacts.add(new ContactData().withId(resultSet.getInt("id"))
                .withFirstName(resultSet.getString("firstname"))
                .withMiddleName(resultSet.getString("middlename"))
                .withLastName(resultSet.getString("lastname"))
                .withNickName(resultSet.getString("nickname"))
                .withCompany(resultSet.getString("company"))
                .withTitle(resultSet.getString("title"))
                .withAddress(resultSet.getString("address"))
                .withHomePhone(resultSet.getString("home"))
                .withMobile(resultSet.getString("mobile"))
                .withWorkPhone(resultSet.getString("home"))
                .withEmail(resultSet.getString("email"))
                .withEmail2(resultSet.getString("email2"))
                .withEmail3(resultSet.getString("email3")));
      }
      resultSet.close();
      st.close();
      conn.close();
      System.out.println(contacts);


      // Do something with the Connection

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
