package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

  public LoginHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String password) {
    wd.get(app.getProperty("web.BaseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.xpath("//input[@type='submit']"));
    waitUntilElementIdAppears(10, "password");
    type(By.name("password"), password);
    click(By.xpath("//input[@type='submit']"));
    isElementPresent(By.xpath("//a[text()='" + username + "']/@href"));
  }
}
