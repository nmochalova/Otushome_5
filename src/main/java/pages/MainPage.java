package pages;

import com.codeborne.selenide.Selenide;

public class MainPage extends BasePage<MainPage>{
  public MainPage open() {
    Selenide.closeWebDriver();
    Selenide.open();
    return this;
  }



}
