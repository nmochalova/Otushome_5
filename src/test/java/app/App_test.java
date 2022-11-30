package app;

import asserts.AssertUser;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import extensions.AppiumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
  private final int countPost = 100;
  private final int millsPause = 600;
  private final int usersPixels = 80;
 // private final int postsPixels = 152;

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
        mainPage.scrollPage(userElement,usersPixels,millsPause);
      }
    }
    //Проверяем, что все данные пользователя отобразились правильно
    String result = userPage.getUserInfo(targetUsername);
    AssertUser.asserDataUser(result, user);
  }

  //Тест, который проверяет, что постов ровно 100 шт
  @Test
  public void scrollTest(){
    mainPage.open();
    postPage.clickToPostsPage();
    int postsPixels = 120;
    int deltasPix = 50;
    int millsPause = 300;
    int i=1;
    boolean isFlag = true;

//    for (int j = 1; j <= countPost; j++) {
//      postPage.scrollUntilFoundPost(j, postsPixels, deltasPix, millsPause);
//    }

    while (isFlag) {
      try {
        postPage.scrollUntilFoundPost(i, postsPixels, deltasPix, millsPause);
        i++;
      } catch (ElementNotFound e) {
        isFlag = false;
      }
    }

    assertEquals(countPost,i);
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

     SelenideElement postElement = postPage.findPost(postid);
     postPage.clickPost(postElement);
     postPage.checkPost(title);

     //Проверяем, что все данные поста отобразились правильно
     String result = postPage.getPostInfo(postid);
     System.out.println(result);
     AssertUser.asserDataPost(result, post);
  }

}
