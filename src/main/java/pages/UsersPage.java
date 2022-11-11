package pages;

import com.codeborne.selenide.Condition;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import services.UserApi;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsersPage extends BasePage<UsersPage>{
  //ждем пока содержимое страницы Users загрузится
  public void checkLoadPage(){
    $(By.className("android.widget.ScrollView")).shouldBe(Condition.visible);
  }

  //Кликаем по пользователю по заданному username
  public void clickUser(String username) {
    String locatorUsername = String.format("[content-desc ^= '%s']",username);
    $(locatorUsername).click();
  }

  //Из данных JSON берем данные пользователя
  public Map<String,String> getUserInfoFromJson(int id){
    UserApi userApi = new UserApi();
    Response response = userApi.getUser("1");
    assertTrue(response.statusCode()==200);

    Map<String,String> user = new HashMap<>();
    user.put("id",userApi.getValueFromJson(response,"id"));
    user.put("name",userApi.getValueFromJson(response,"name"));
    user.put("username",userApi.getValueFromJson(response,"username"));
    user.put("email",userApi.getValueFromJson(response,"email"));
    user.put("phone",userApi.getValueFromJson(response,"phone"));
    user.put("website",userApi.getValueFromJson(response,"website"));

    return user;
  }

}
