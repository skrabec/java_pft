package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
  private WebDriver wd;
  private final ApplicationManager app;

  public RegistrationHelper(ApplicationManager app) {
    this.app = app;
    wd = app.getDriver();
  }


  public void start(String username, String email) {
    wd.get(app.getProperty("web.BaseUrl") + "/signup_page.php");
  }
}
