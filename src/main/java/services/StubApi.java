package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class StubApi {
  private String baseStubUrl =  System.getProperty("wiremock.hostname","http://localhost:9190");
  private String pingPath = "/ping";
  private RequestSpecification reqSpec;
  public StubApi() {
    reqSpec = given()
            .baseUri(baseStubUrl)
            .contentType(ContentType.JSON);
  }

  public Response getPing() {
    Response response = RestAssured
            .given(reqSpec)
            .basePath(pingPath)
            .log().all()
            .when()
            .get()
            .andReturn();

    response.prettyPrint();

    return response;
  }
}
