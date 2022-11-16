package asserts;

import org.junit.jupiter.api.Assertions;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertUser {

  public static void asserDataUser(String result, Map<String,String> user) {
    Assertions.assertAll(() -> {
      assertTrue(result.contains(user.get("name")));
      assertTrue(result.contains(user.get("username")));
      assertTrue(result.contains(user.get("phone")));
      assertTrue(result.contains(user.get("website")));
      assertTrue(result.contains(user.get("city")));
      assertTrue(result.contains(user.get("street")));
      assertTrue(result.contains(user.get("suite")));
      assertTrue(result.contains(user.get("zipcode")));
    });
  }

  public static void asserDataPost(String result, Map<String, String> post) {
    Assertions.assertAll(() -> {
      assertTrue(result.contains(post.get("id")));
      assertTrue(result.contains(post.get("title")));
      assertTrue(result.contains(post.get("body")));
    });
  }
}
