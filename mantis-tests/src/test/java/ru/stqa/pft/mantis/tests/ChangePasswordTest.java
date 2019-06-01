package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.Model.MailMessage;

import javax.mail.MessagingException;
import java.util.List;

public class ChangePasswordTest extends TestBase {

  @Test
  public void testChangePassword() throws MessagingException {
    String username = "user1559392134489";
    String password = "password";
    String newPassword = "1234";
    String email = "user1559242437358@localhost.localdomain";
    String admin = "Administrator";
    String adminpass = "root";
    app.login().start(admin, adminpass);
    app.manageUsers().resetPassword(username);
    List<MailMessage> mailMessages = app.james().waitForMail(username, password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    //app.manageUsers().applyNewPassword(confirmationLink, newPassword);

  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}