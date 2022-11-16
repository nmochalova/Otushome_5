package pages;

import io.restassured.response.Response;
import services.UserApi;

import java.util.HashMap;
import java.util.Map;

public class PostsPage extends MainPage{
  public Map<String, String> getPostInfoFromJson(String postid) {
    UserApi userApi = new UserApi();
    Response response = userApi.getPost(postid);

    Map<String,String> post = new HashMap<>();
    post.put("userId",userApi.getValueFromJson(response,"userId"));
    post.put("id",userApi.getValueFromJson(response,"id"));
    post.put("title",userApi.getValueFromJson(response,"title"));
    post.put("body",userApi.getValueFromJson(response,"body"));

    return post;
  }
}
