package gorest.util.rest;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import gorest.configuration.Configuration;

import static com.jayway.restassured.RestAssured.given;

public class Transfer {

    private Configuration configuration = new Configuration();

    private final String token = "037275e7b1bde71aec5f1c6fb4f0f291b36eee25cf8a4707c205ab979abc6908";

    public Response get(String url) {
        return given().spec(getBaseRequesBuilder().build())
                .when().get(url)
                .then().log().all()
                .extract().response();
    }

    public Response post(String url, String body){
        return given().spec(getBaseRequesBuilderAuth().build())
                .body(body)
                .when().post(url)
                .then().log().all()
                .extract().response();
    }

    public Response createNewPost(String url, String body){
        return given().spec(getBaseRequesBuilderAuth().build())
                .body(body)
                .when().post(url)
                .then().log().all()
                .extract().response();
    }

    public Response delete(String url, int id) {
        return given().spec(getBaseRequesBuilderAuth().build())
                .when().delete(url + "/" + id)
                .then().log().all()
                .extract().response();
    }

    public Response put(String url, String body, int id) {
        return given().spec(getBaseRequesBuilderAuth().build())
                .body(body)
                .when().put(url+ "/" + id)
                .then().log().all()
                .extract().response();
    }

    private RequestSpecBuilder getBaseRequesBuilder() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setBaseUri(configuration.getValue(Configuration.Options.BASE_PATH))
                .setRelaxedHTTPSValidation();
    }

    private RequestSpecBuilder getBaseRequesBuilderAuth() {
        return new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setBaseUri(configuration.getValue(Configuration.Options.BASE_PATH))
                .setRelaxedHTTPSValidation();
    }
}
