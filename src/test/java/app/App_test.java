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

// clean test -DforkСount=0 -Duserid=2 -Dtest=App_test#check_user_by_id_test
@ExtendWith(AppiumExtension.class)
public class App_test {
  private MainPage mainPage = new MainPage();
  private UsersPage usersPage = new UsersPage();
  private UserPage userPage = new UserPage();

  //Тест, который кликает по пользователю с заданным ID = (1,2,3) и проверяет, что отобразилась информация именно по этому пользователю
  @Test
  public void checkUserByIdTest() {
    mainPage.open();
    usersPage.check();

    String userId = System.getProperty("userid","1");
    if (Integer.parseInt(userId) > 3)  userId = "1";

    Map<String,String> user = usersPage.getUserInfoFromJson(userId);
    String name = user.get("name");
    String username = user.get("username");

    SelenideElement userElement = userPage.findUser(username);
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
