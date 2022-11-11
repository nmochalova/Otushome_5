package app;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import extensions.AppiumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.UserPage;
import pages.UsersPage;

import static com.codeborne.selenide.Selenide.$;

@ExtendWith(AppiumExtension.class)
public class App_test {
  private MainPage mainPage = new MainPage();
  private UsersPage usersPage = new UsersPage();
  private UserPage userPage = new UserPage();

  //Тест, который проверяет, что на странице Posts загружено 100 комментариев
  @Test
  public void check_posts_test() {
    mainPage.open();

    $(By.className("android.widget.ScrollView")).shouldBe(Condition.visible);
    $("[content-desc *= 'Tab 2 of 2']").click();

    $("android.widget.ScrollView .android.view.View").should(Condition.visible);
    String str = $("[content-desc *= 'post id: 1']").getAttribute("content-desc");
    System.out.println(str);


//    for (int i = 0; i < 10; i++) {
//      Selenide.actions().scrollByAmount(0,100);
//      ElementsCollection posts = $$("[content-desc *= 'post id:']");
//      System.out.println(posts.size());
//      System.out.println(posts.first().getAttribute("content-desc"));
//    }

 //   $("[content-desc *= 'post id: 1']").click();
  }

  //Тест, который кликает по пользователю с заданным ID и проверяет, что отобразилась информация именно по этому пользователю
  @Test
  public void check_user_by_id_test() {
    mainPage.open();
    usersPage.checkLoadPage();
    //Из данных JSON берем данные пользователя
    //...
    String username = "Bret";
    String name = "Leanne Graham";

    //ищем локатор на экране, если не находим делаем свайп пока не найдем
    //..
    //если не нашли и конец страницы, то ошибка: данные по пользователю не найдены
    //..
    //если нашли - кликаем по пользователю
    usersPage.clickUser(username);
    userPage.checkUser(name);
    //Проверяем, что все данные пользователя отобразились правильно
    String result = userPage.getUserInfo(username);
    System.out.println(result);
    //..
  }

  //Тест, который кликает по комментарию с заданным ID и проверяет, что отобразилась информация именно по этому комментарию
  @Test
  public void check_post_by_id_test() {
    mainPage.open();

    $(By.className("android.widget.ScrollView")).shouldBe(Condition.visible);
    $("[content-desc *= 'Tab 2 of 2']").click();

    $("[content-desc *= 'post id: 1']").click();
  }

}
