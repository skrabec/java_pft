package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.Model.MailMessage;
import ru.stqa.pft.mantis.Model.UserData;
import ru.stqa.pft.mantis.Model.Users;

import javax.mail.MessagingException;
import java.util.List;

public class ChangePasswordTest extends TestBase {

  @Test
  public void testChangePassword() throws MessagingException {
    String password = "password";
    String newPassword = "1234";
    String admin = "Administrator";
    String adminpass = "root";
    Users users = app.db().users();
    UserData user = users.iterator().next();
    app.login().start(admin, adminpass);
    app.manageUsers().resetPassword(user.getUsername());
    List<MailMessage> mailMessages = app.james().waitForMail(user.getUsername(), password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
    //app.manageUsers().applyNewPassword(confirmationLink, newPassword);

  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}