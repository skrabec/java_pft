package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void manageUserPage() {
    wd.get("http://localhost/mantisbt-2.21.0/manage_user_page.php");
  }
}
