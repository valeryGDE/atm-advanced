package main.java.api.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SlackRestUtil {

    private static final String slackURL = System.getenv("SLACK_WEBHOOK");

    public RequestSpecification initSlack() {

        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(slackURL)
                .setUrlEncodingEnabled(false)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
}