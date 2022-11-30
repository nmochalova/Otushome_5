package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class UserPage extends MainPage{
  //Возвращаем данные пользователя
  public String getUserInfo(String username) {
    String locatorUsername = String.format("[content-desc ^= '%s']",username);
    SelenideElement el_2 = $(locatorUsername);
    return el_2.getAttribute("content-desc");
  }

  //Проверяем, что открылись данные по пользователю name
  public void checkUser(String name) {
    String locatorName = String.format("[content-desc ^= 'User %s']",name);
    $(locatorName).shouldBe(Condition.visible);
  }

  public SelenideElement findUser(String username){
    String locatorUsername = String.format("[content-desc ^= '%s']",username);

    SelenideElement element = null;
    try {
      element = $(locatorUsername).should(Condition.visible);
    } catch (ElementNotFound e) {
      throw e;
    }

    return element;
  }


}
