package main.java.api.integration;

import com.google.inject.Inject;
import io.restassured.response.Response;
import main.java.api.rest.JiraRestUtil;

import static io.restassured.RestAssured.given;

public class JiraService {

    @Inject
    JiraRestUtil jiraRestUtil;

    public Response addCommentToIssue(String comment, String issue) {
        return given().
                spec(jiraRestUtil.initJira()).
                when().
                body(String.format("{\"visibility\": null," +
                        "\"body\":{\"type\":\"doc\",\"version\":1,\"content\":[{\"type\":\"paragraph\"," +
                        "\"content\":[{\"text\":\"%s\"" +
                        ",\"type\":\"text\"}]}]}}", comment)).
                post(String.format("issue/%s/comment", issue));
    }

    public Response addStatusToIssue(int status, String issue) {
        return given().
                spec(jiraRestUtil.initJira()).
                when().
                body(String.format("{\"transition\":{\"id\":\"%s\"}}", status)).
                post(String.format("issue/%s/transitions", issue));
    }
}
