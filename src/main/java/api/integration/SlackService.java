package main.java.api.integration;

import com.google.inject.Inject;
import io.restassured.response.Response;
import main.java.api.rest.SlackRestUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SlackService {

    @Inject
    private SlackRestUtil slackRestUtil;

    public Response postNotificationToSlack(String comment) {
        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("text", comment);
        return given().
                spec(slackRestUtil.initSlack()).
                body(jsonBody).
                when().
                post("");
    }
}
