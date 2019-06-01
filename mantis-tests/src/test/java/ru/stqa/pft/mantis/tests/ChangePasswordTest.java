package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

public class ChangePasswordTest extends TestBase{

  @Test
  public void testChangePassword(){
    app.login().start("administrator", "root");
  }
}
