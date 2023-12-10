package test.api.httpclient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.api.datamodels.Condition;
import main.java.api.datamodels.Filter;
import main.java.api.datamodels.Order;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static test.api.restassured.FiltersApiTest.EMPTY_NAME_ERROR_MESSAGE;
import static test.api.restassured.FiltersApiTest.WRONG_ID_ERROR_MESSAGE;

public class FiltersHttpClientTest extends BaseHttpClientTest {

    @Test
    public void getAllProjectFiltersTest() throws IOException {
        try (CloseableHttpResponse response = executeGetRequest("filter")) {
            int statusCode = response.getStatusLine().getStatusCode();
            var responseBody = EntityUtils.toString(response.getEntity());
            assertThat("Unexpected status code", statusCode, equalTo(200));
            var objectMapper = new ObjectMapper();
            var jsonNode = objectMapper.readTree(responseBody);
            assertThat("Unexpected content size", jsonNode.get("content").size(), greaterThan(0));
        }
    }

    @Test
    public void error404OnGetAllProjectFiltersTest() throws IOException {
        try (CloseableHttpResponse response = executeGetRequest("filters")) {
            int statusCode = response.getStatusLine().getStatusCode();
            var responseBody = EntityUtils.toString(response.getEntity());
            assertThat("Unexpected status code", statusCode, equalTo(404));
            var objectMapper = new ObjectMapper();
            var jsonResponse = objectMapper.readValue(responseBody, Map.class);
            assertThat("Unexpected error value", jsonResponse.get("error"), equalTo("Not Found"));
        }
    }

    @Test
    public void createFilterSuccessTest() throws IOException {
        var filter = new Filter();
        filter.setName("Http client new filter");
        filter.setType("launch");
        filter.setOrders(new ArrayList<>(Collections.singletonList(new Order(true, "number"))));
        filter.setConditions(new ArrayList<>(Collections.singletonList(new Condition("cnt", "name", "Lollipop"))));

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(filter);

        try (CloseableHttpResponse response = executePostRequest("filter", jsonBody)) {
            int statusCode = response.getStatusLine().getStatusCode();
            var responseBody = EntityUtils.toString(response.getEntity());
            assertThat("Unexpected status code", statusCode, equalTo(201));
            assertThat("Response body does not contain 'id'", responseBody, notNullValue());
        }
    }

    @Test
    public void createFilterWithMissingNameTest() throws IOException {
        var filter = new Filter();
        filter.setName("");
        filter.setType("launch");
        filter.setOrders(new ArrayList<>(Collections.singletonList(new Order(true, "number"))));
        filter.setConditions(new ArrayList<>(Collections.singletonList(new Condition("cnt", "name", "Lollipop"))));

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(filter);

        try (CloseableHttpResponse response = executePostRequest("filter", jsonBody)) {
            int statusCode = response.getStatusLine().getStatusCode();
            var responseBody = EntityUtils.toString(response.getEntity());
            assertThat("Unexpected status code", statusCode, equalTo(400));
            assertThat("Response body does not contain 'id'", responseBody, containsString(EMPTY_NAME_ERROR_MESSAGE));
        }
    }

    @Test
    public void noRequestBodyOnPostFilter400ErrorTest() throws IOException {
        try (CloseableHttpResponse response = executePostRequest("filter", "")) {
            int statusCode = response.getStatusLine().getStatusCode();
            var responseBody = EntityUtils.toString(response.getEntity());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(responseBody);

            assertThat("Unexpected status code", statusCode, equalTo(400));
            assertThat("Unexpected error code", jsonResponse.get("errorCode").asInt(), equalTo(4001));
            assertThat("Response body does not contain expected message", jsonResponse.get("message").asText(),
                    containsString("Incorrect Request. Required request body is missing"));
        }
    }

    @Test
    public void updateFilterByIdTest() throws IOException {
        int id;
        var filter = new Filter();
        filter.setName("Http Client filter for update");
        filter.setType("launch");
        filter.setOrders(new ArrayList<>(Collections.singletonList(new Order(true, "number"))));
        filter.setConditions(new ArrayList<>(Collections.singletonList(new Condition("cnt", "name", "Lollipop"))));

        ObjectMapper objectMapper = new ObjectMapper();
        var jsonBody = objectMapper.writeValueAsString(filter);

        try (CloseableHttpResponse response = executePostRequest("filter", jsonBody)) {
            var responseBody = EntityUtils.toString(response.getEntity());
            JsonNode jsonResponse = objectMapper.readTree(responseBody);
            id = jsonResponse.get("id").asInt();
        }

        var newName = "Changed Http Client filter";
        filter.setName(newName);
        jsonBody = objectMapper.writeValueAsString(filter);
        try (CloseableHttpResponse response = executePutRequest("filter/" + id, jsonBody)) {
            int statusCode = response.getStatusLine().getStatusCode();
            assertThat("Unexpected status code", statusCode, equalTo(200));
        }

        try (CloseableHttpResponse response = executeGetRequest("filter/" + id)) {
            int statusCode = response.getStatusLine().getStatusCode();
            var responseBody = EntityUtils.toString(response.getEntity());
            assertThat("Unexpected status code", statusCode, equalTo(200));
            var jsonNode = objectMapper.readTree(responseBody);
            assertThat("Unexpected content size", jsonNode.get("name").asText(), equalTo(newName));
        }
    }

    @Test
    public void updateFilterWithInvalidIdTest() throws IOException {
        var fakeId = 0;
        var filter = new Filter();
        filter.setName("Http Client filter for update");
        filter.setType("launch");
        filter.setOrders(new ArrayList<>(Collections.singletonList(new Order(true, "number"))));
        filter.setConditions(new ArrayList<>(Collections.singletonList(new Condition("cnt", "name", "Lollipop"))));

        ObjectMapper objectMapper = new ObjectMapper();
        var jsonBody = objectMapper.writeValueAsString(filter);

        try (CloseableHttpResponse response = executePutRequest("filter/" + fakeId, jsonBody)) {
            int statusCode = response.getStatusLine().getStatusCode();
            assertThat("Unexpected status code", statusCode, equalTo(404));
        }
    }

    @Test
    public void updateFilterWithMissingNameTest() throws IOException {
        int id;
        var filter = new Filter();
        filter.setName("Http Client filter with missing name");
        filter.setType("launch");
        filter.setOrders(new ArrayList<>(Collections.singletonList(new Order(true, "number"))));
        filter.setConditions(new ArrayList<>(Collections.singletonList(new Condition("cnt", "name", "Lollipop"))));

        ObjectMapper objectMapper = new ObjectMapper();
        var jsonBody = objectMapper.writeValueAsString(filter);

        try (CloseableHttpResponse response = executePostRequest("filter", jsonBody)) {
            var responseBody = EntityUtils.toString(response.getEntity());
            JsonNode jsonResponse = objectMapper.readTree(responseBody);
            id = jsonResponse.get("id").asInt();
        }

        var newName = "";
        filter.setName(newName);
        jsonBody = objectMapper.writeValueAsString(filter);
        try (CloseableHttpResponse response = executePutRequest("filter/" + id, jsonBody)) {
            int statusCode = response.getStatusLine().getStatusCode();
            var responseBody = EntityUtils.toString(response.getEntity());
            JsonNode jsonResponse = objectMapper.readTree(responseBody);
            assertThat("Unexpected status code", statusCode, equalTo(400));
            assertThat("Unexpected error code", jsonResponse.get("errorCode").asInt(), equalTo(4001));
            assertThat("Response body does not contain expected message", jsonResponse.get("message").asText(),
                    containsString(EMPTY_NAME_ERROR_MESSAGE));
        }
    }

    @Test
    public void deleteFilterSuccessTest() throws IOException {
        int id;
        var filter = new Filter();
        filter.setName("Http Client filter for Delete");
        filter.setType("launch");
        filter.setOrders(new ArrayList<>(Collections.singletonList(new Order(true, "number"))));
        filter.setConditions(new ArrayList<>(Collections.singletonList(new Condition("cnt", "name", "Lollipop"))));

        ObjectMapper objectMapper = new ObjectMapper();
        var jsonBody = objectMapper.writeValueAsString(filter);

        try (CloseableHttpResponse response = executePostRequest("filter", jsonBody)) {
            var responseBody = EntityUtils.toString(response.getEntity());
            JsonNode jsonResponse = objectMapper.readTree(responseBody);
            id = jsonResponse.get("id").asInt();
        }

        try (CloseableHttpResponse response = executeGetRequest("filter/" + id)) {
            int statusCode = response.getStatusLine().getStatusCode();
            var responseBody = EntityUtils.toString(response.getEntity());
            assertThat("Unexpected status code", statusCode, equalTo(200));
            var jsonNode = objectMapper.readTree(responseBody);
            assertThat("Unexpected content size", jsonNode.get("id").asInt(), equalTo(id));
        }

        try (CloseableHttpResponse response = executeDeleteRequest("filter/" + id)) {
            int statusCode = response.getStatusLine().getStatusCode();
            assertThat("Unexpected status code", statusCode, equalTo(200));
        }

        try (CloseableHttpResponse response = executeGetRequest("filter/" + id)) {
            int statusCode = response.getStatusLine().getStatusCode();
            assertThat("Unexpected status code", statusCode, equalTo(404));
        }
    }

    @Test
    public void deleteFilterWithInvalidIdTest() throws IOException {
        var fakeId = 0;
        try (CloseableHttpResponse response = executeDeleteRequest("filter/" + fakeId)) {
            int statusCode = response.getStatusLine().getStatusCode();
            var responseBody = EntityUtils.toString(response.getEntity());
            assertThat("Unexpected status code", statusCode, equalTo(404));
            assertThat("Response body does not contain 'id'", responseBody, containsString(WRONG_ID_ERROR_MESSAGE));
        }
    }
}
