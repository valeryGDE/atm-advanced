package main.java.api.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import main.java.ui.core.properties.IntegrationProperty;
import main.java.ui.core.properties.PropertyReader;

import java.util.Base64;

public class JiraRestUtil {
    private static final String emailURL = PropertyReader.getProperty(IntegrationProperty.EMAIL_URL.getKey());
    private static final String jiraURL = PropertyReader.getProperty(IntegrationProperty.JIRA_URL.getKey());

    public RequestSpecification initJira() {
        var encoding = Base64.getEncoder().encodeToString((emailURL + ":" + System.getenv("JIRA_TOKEN")).getBytes());
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("Authorization", "Basic " + encoding)
                .setBaseUri(jiraURL + "/rest/api/3/")
                .setUrlEncodingEnabled(false)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
}
