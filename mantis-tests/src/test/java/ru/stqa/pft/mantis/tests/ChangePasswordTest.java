package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.Model.MailMessage;
import ru.stqa.pft.mantis.Model.UserData;
import ru.stqa.pft.mantis.Model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ChangePasswordTest extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws MessagingException, IOException {
    String newPassword = "5555";
    String admin = "Administrator";
    String adminpass = "root";
    Users withoutAdmin = app.db().users().stream().filter((a) -> !a.getEmail().equals("root@localhost")).collect(Collectors.toCollection(Users::new));
    UserData user = withoutAdmin.iterator().next();
    app.login().start(admin, adminpass);
    app.manageUsers().resetPassword(user.getUsername());
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    //List<MailMessage> mailMessages = app.james().waitForMail(user.getUsername(), password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
    app.manageUsers().applyNewPassword(confirmationLink, newPassword);
    Assert.assertTrue(app.newSession().login(user.getUsername(), newPassword));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod
  public void stopMailServer() {
    app.mail().stop();
  }
}