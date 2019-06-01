package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ManageHelper extends HelperBase {

  public ManageHelper(ApplicationManager app) {
    super(app);
  }

  public void resetPassword(String username){
    app.goTo().manageUserPage();
    wd.findElement(By.linkText(username)).click();
    wd.findElement(By.xpath("//input[@value='Reset Password']")).click();
  }

  public void applyNewPassword(String confirmationLink, String password){
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.xpath("//button[@type='submit']"));
  }


}
