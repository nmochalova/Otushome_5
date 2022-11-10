package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import providers.SelenideWebDriver;

import java.time.Duration;

public class MainPage extends BasePage<MainPage>{
  public MainPage open() {
    Selenide.open();
    return this;
  }

}
