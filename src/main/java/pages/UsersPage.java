package pages;

import com.codeborne.selenide.Selenide;

public class UsersPage extends BasePage<UsersPage>{
  public UsersPage open() {
    Selenide.open();
    return this;
  }

}
