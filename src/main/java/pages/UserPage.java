package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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
    boolean endOfScreen = false;

    while (!endOfScreen) {
      ElementsCollection user = $$(locatorUsername);
      if (user.size() == 1) { //если нашли хотя бы один элемент, то возвращаем его
        return user.get(0);
      } else {  //иначе продолжаем скроллить экран
        endOfScreen = swapeScreen();
      }
    }

    return null; //если ничего не нашли возвращаем null
  }

  private boolean swapeScreen() {
    return true;
  }
}
