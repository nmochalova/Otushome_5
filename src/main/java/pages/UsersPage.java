package pages;

import com.codeborne.selenide.SelenideElement;
import io.restassured.response.Response;
import services.UserApi;

import java.util.HashMap;
import java.util.Map;

public class UsersPage extends MainPage{
  public void clickUser(SelenideElement user, String userId) {
      user.click();
  }

  public Map<String,String> getUserInfoFromJson(String id){
    UserApi userApi = new UserApi();
    Response response = userApi.getUser(id);

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

  public String getUserUsernameById(String userId) {
    Map<String,String> user = getUserInfoFromJson(userId);
    return user.get("username");
  }

}
