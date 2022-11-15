package app;

import asserts.AssertUser;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import extensions.AppiumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.UserPage;
import pages.UsersPage;

import java.util.Map;
import static com.codeborne.selenide.Selenide.$;

//Работает только второй тест, команда запуска:
// clean test -DforkСount=0 -Duserid=2 -Dtest=App_test#check_user_by_id_test
//Работает только для id пользователей 1 и 2.
//Скролл экрана в Selenide не поддерживается.
@ExtendWith(AppiumExtension.class)
public class App_test {
  private MainPage mainPage = new MainPage();
  private UsersPage usersPage = new UsersPage();
  private UserPage userPage = new UserPage();

  //Тест, который проверяет, что на странице Posts загружено 100 комментариев
  @Test
  public void check_posts_test() {
    //В РАБОТЕ
    mainPage.open();
    $(By.className("android.widget.ScrollView")).shouldBe(Condition.visible);
    $("[content-desc *= 'Tab 2 of 2']").click();
    $("android.widget.ScrollView .android.view.View").should(Condition.visible);
    String str = $("[content-desc *= 'post id: 1']").getAttribute("content-desc");
    System.out.println(str);
  }

  //Тест, который кликает по пользователю с заданным ID и проверяет, что отобразилась информация именно по этому пользователю
  @Test
  public void check_user_by_id_test() {
    mainPage.open();
    usersPage.check();

    String userId = System.getProperty("userid","10");
    Map<String,String> user = usersPage.getUserInfoFromJson(userId);
    String name = user.get("name");
    String username = user.get("username");

    //ищем локатор на экране, если не находим делаем свайп пока не найдем
    SelenideElement userElement = userPage.findUser(username); //<-- прокрутка экрана не работает!

    //если нашли - кликаем по пользователю
    usersPage.clickUser(userElement,userId);
    userPage.checkUser(name);
    //Проверяем, что все данные пользователя отобразились правильно
    String result = userPage.getUserInfo(username);
    System.out.println(result);
    AssertUser.asserDataUser(result, user);
  }

  //Тест, который кликает по комментарию с заданным ID и проверяет, что отобразилась информация именно по этому комментарию
  @Test
  public void check_post_by_id_test() {
    //В РАБОТЕ
    mainPage.open();
    $(By.className("android.widget.ScrollView")).shouldBe(Condition.visible);
    $("[content-desc *= 'Tab 2 of 2']").click();
    $("[content-desc *= 'post id: 1']").click();
  }

}
