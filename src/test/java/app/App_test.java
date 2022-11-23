package app;

import asserts.AssertUser;
import com.codeborne.selenide.SelenideElement;
import extensions.AppiumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.*;
import stubs.PingStub;

import java.util.Map;

//clean test -DforkСount=0 -Dtest=App_test

@ExtendWith(AppiumExtension.class)
public class App_test {
//  {
//    new PingStub();
//  }
  private MainPage mainPage = new MainPage();
  private UsersPage usersPage = new UsersPage();
  private UserPage userPage = new UserPage();
  private PostPage postPage = new PostPage();
  private PostsPage postsPage = new PostsPage();

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
