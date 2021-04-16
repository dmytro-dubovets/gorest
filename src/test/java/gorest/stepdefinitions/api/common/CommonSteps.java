package gorest.stepdefinitions.api.common;

import gorest.util.rest.Context;

import static org.assertj.core.api.Assertions.assertThat;

public class CommonSteps {

    public <T> void verifyResponse(Context<T> context, int statusCode) {
        performStatusCodeCheck(context.getResponseStatusCode(), statusCode);
    }

    private void performStatusCodeCheck(int actualCode, int expectedCode) {
        assertThat(actualCode).isEqualTo(expectedCode);
    }

}
