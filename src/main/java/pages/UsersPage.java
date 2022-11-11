package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class UsersPage extends BasePage<UsersPage>{
  //ждем пока содержимое страницы Users загрузится
  public void checkLoadPage(){
    $(By.className("android.widget.ScrollView")).shouldBe(Condition.visible);
  }

  //Кликаем по пользователю по заданному username
  public void clickUser(String username) {
    String locatorUsername = String.format("[content-desc ^= '%s']",username);
    $(locatorUsername).click();
  }


}
