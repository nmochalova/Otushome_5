package app;

import asserts.AssertUser;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import extensions.AppiumExtension;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofMillis;
import static java.util.Collections.singletonList;

//clean test -DforkСount=0 -Dtest=App_test

@ExtendWith(AppiumExtension.class)
public class App_test {
  private MainPage mainPage = new MainPage();
  private UsersPage usersPage = new UsersPage();
  private UserPage userPage = new UserPage();
  private PostPage postPage = new PostPage();
  private PostsPage postsPage = new PostsPage();

  @Test
  public void scrollTest(){
    mainPage.open();

    for (int i = 1; i < 11; i++) {

      String userId = String.valueOf(i);
      Map<String, String> user = usersPage.getUserInfoFromJson(userId);
      String username = user.get("username");
      Point source = userPage.findUser(username).getLocation();
      System.out.println("x = " + source.x + ", y = " + source.y);

      PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
      Sequence sequence = new Sequence(finger, 1);

      //Движение указателя к позиции User_i
      sequence.addAction(finger.createPointerMove(ofMillis(0),
              PointerInput.Origin.viewport(), source.x, source.y)); //x1,y1
      //Движение указателя вниз.
      sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
      //Удержание указателя на некоторое время (в миллисекундах).
      sequence.addAction(new Pause(finger, ofMillis(600)));
      //Движение указателя со слайдером к конечной локации.
      sequence.addAction(finger.createPointerMove(ofMillis(600),
              PointerInput.Origin.viewport(), source.x, source.y - 80)); //x1,y2
      //“Отрыв” указателя от слайдера.
      sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

      RemoteWebDriver driver = (RemoteWebDriver) Selenide.webdriver().object();
      driver.perform(Arrays.asList(sequence));
    }
  }

  //Тест, который кликает по пользователю с заданным ID = (1,2,3) и проверяет, что отобразилась информация именно по этому пользователю
  @Test
  public void checkUserByIdTest() {
    mainPage.open();

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
  public void checkPostByIdTest() {
     mainPage.open();
     postPage.clickToPostsPage();

     String postid = System.getProperty("postid","1");
     if (Integer.parseInt(postid) != 1)  postid = "1";

     Map<String,String> post = postsPage.getPostInfoFromJson(postid);
     String title = post.get("title");

     SelenideElement postElement = postPage.findUser(postid);
     postPage.clickPost(postElement);
     postPage.checkPost(title);

     //Проверяем, что все данные поста отобразились правильно
     String result = postPage.getPostInfo(postid);
     System.out.println(result);
     AssertUser.asserDataPost(result, post);
  }

}
