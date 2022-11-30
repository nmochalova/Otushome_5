package services;

import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class UserApi {
  private String baseUrl = System.getProperty("base.url","https://jsonplaceholder.typicode.com");
  private String userId = "/users/{id}";
  private String postId = "/posts/{id}";
  private String listPosts = "/posts/}";

  private RequestSpecification reqSpec;

  public UserApi() {
    reqSpec = given()
            .baseUri(baseUrl)
            .contentType(ContentType.JSON);
  }

  public Response getUser(String id) {
    Response response = RestAssured
            .given(reqSpec)
            .log().all()
            .when()
            .get(userId,id)
            .andReturn();

    response.prettyPrint();

    return response;
  }

  public Response getPost(String id) {
    Response response = RestAssured
            .given(reqSpec)
  //          .log().all()
            .when()
            .get(postId,id)
            .andReturn();

 //   response.prettyPrint();

    return response;
  }

  public ValidatableResponse getListPosts() {
    return given(reqSpec)
            .basePath(listPosts)
            .log().all()
            .when()
            .get()
            .then()
            .statusCode(200)
            .log().all();
  }

  //Метод, который проверяет наличие поля name и возвращает его значение
  public String getValueFromJson(Response response, String name) {
    response
            .then()
            .assertThat()
            .defaultParser(Parser.JSON)
            .body( "$",hasKey(name)); //$ - ищем поле в корне json

    return response.jsonPath().getString(name);
  }

  public String getValueFromJson(Response response, String path, String name) {
    return response.jsonPath().getString(path+"."+name);
  }

}
