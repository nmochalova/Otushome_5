package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
  public MainPage open() {
    Selenide.closeWebDriver();
    Selenide.open();
    check();

    return this;
  }

  public void check(){
    $(By.xpath("//android.widget.ScrollView/android.view.View")).shouldBe(Condition.visible);
  }

}
