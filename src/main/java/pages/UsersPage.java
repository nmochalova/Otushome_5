package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import exseptions.UserNotFound;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import services.UserApi;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsersPage extends BasePage<UsersPage>{
  //ждем пока содержимое страницы Users загрузится
  public void check(){
   // $(By.className("android.widget.ScrollView")).shouldBe(Condition.visible);
    $(By.xpath("//android.widget.ScrollView/android.view.View")).shouldBe(Condition.visible);
  }

  //Кликаем по пользователю
  public void clickUser(SelenideElement user, String userId) {
    try {

      user.click(); //<--

    } catch (NullPointerException e) {
      try {
        throw new UserNotFound(userId);
      } catch (UserNotFound ex) {
        ex.printStackTrace();
      }
    }
  }

  //Из данных JSON берем данные пользователя
  public Map<String,String> getUserInfoFromJson(String id){
    UserApi userApi = new UserApi();
    Response response = userApi.getUser(id);
    assertTrue(response.statusCode()==200);

    Map<String,String> user = new HashMap<>();
    user.put("id",userApi.getValueFromJson(response,"id"));
    user.put("name",userApi.getValueFromJson(response,"name"));
    user.put("username",userApi.getValueFromJson(response,"username"));
    user.put("email",userApi.getValueFromJson(response,"email"));
    user.put("phone",userApi.getValueFromJson(response,"phone"));
    user.put("website",userApi.getValueFromJson(response,"website"));
    user.put("street",userApi.getValueFromJson(response,"address","street"));
    user.put("suite",userApi.getValueFromJson(response,"address","suite"));
    user.put("zipcode",userApi.getValueFromJson(response,"address","zipcode"));
    user.put("city",userApi.getValueFromJson(response,"address","city"));

    return user;
  }

}
