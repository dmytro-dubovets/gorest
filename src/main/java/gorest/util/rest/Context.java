package gorest.util.rest;

import com.jayway.restassured.response.ExtractableResponse;
import com.jayway.restassured.response.Response;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Context<T> {

    private Response response;

    private Class<T> cls;

    public int getResponseStatusCode() {
        return response.getStatusCode();
    }

    public T getObjectFromResponse() {
        return getExtractor().as(cls);
    }

    private ExtractableResponse<Response> getExtractor() {
        return response.then().log().all().extract();
    }

}
