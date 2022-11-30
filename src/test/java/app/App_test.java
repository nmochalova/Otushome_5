package app;

import asserts.AssertUser;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import extensions.AppiumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.*;
import java.util.Map;

//clean test -DforkСount=0 -Dtest=App_test
@ExtendWith(AppiumExtension.class)
public class App_test {
  private MainPage mainPage = new MainPage();
  private UsersPage usersPage = new UsersPage();
  private UserPage userPage = new UserPage();
  private PostPage postPage = new PostPage();
  private PostsPage postsPage = new PostsPage();
  private final int countUser = 10;

  @Test
  public void scrollTest(){
    mainPage.open();

    for (int i = 1; i < 11; i++) {

      String userId = String.valueOf(i);
      Map<String, String> user = usersPage.getUserInfoFromJson(userId);
      String username = user.get("username");
      SelenideElement element = userPage.findUser(username);

      mainPage.scrollPage(element,82,600);
    }
  }

  //Тест, который кликает по пользователю с заданным ID ( <= 10) и проверяет, что отобразилась информация именно по этому пользователю
  @Test
  public void checkUserByIdTest() {
    mainPage.open();

    String userId = System.getProperty("userid","1");
    if ((Integer.parseInt(userId) > countUser) || (Integer.parseInt(userId) <= 0))  userId = "1";

    Map<String,String> user = usersPage.getUserInfoFromJson(userId);
    String targetUsername = user.get("username");
    String targetName = user.get("name");

    for (int i = 1; i <= countUser; i++) {
      try { //ищем целевого пользователя на странице
        SelenideElement userElement = userPage.findUser(targetUsername);
        usersPage.clickUser(userElement, userId);
        userPage.checkUser(targetName);
        break;
      } catch (ElementNotFound e) { //если не нашли прокручиваем страницу
        String otherUserName = usersPage.getUserUsernameById(String.valueOf(i));
        SelenideElement userElement = userPage.findUser(otherUserName);
        mainPage.scrollPage(userElement,80,600);
      }
    }
    //Проверяем, что все данные пользователя отобразились правильно
    String result = userPage.getUserInfo(targetUsername);
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
