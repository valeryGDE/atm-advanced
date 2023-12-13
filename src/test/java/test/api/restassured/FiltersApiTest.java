package test.api.restassured;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.http.ContentType;
import main.java.api.datamodels.Condition;
import main.java.api.datamodels.Filter;
import main.java.api.datamodels.Order;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FiltersApiTest extends BaseApiTest {

    public static final String EMPTY_NAME_ERROR_MESSAGE = "Incorrect Request. [Field 'name' should not contain only white spaces and shouldn't be empty. Field 'name' should have size from '3' to '128'.] ";
    public static final String WRONG_ID_ERROR_MESSAGE = "User filter with ID '0' not found on project";

    @Test
    public void getAllProjectFiltersTest() {
        var response = given().spec(requestSpecification)
                .when()
                .get("filter")
                .andReturn();
        List<Filter> filters = response.jsonPath().getList("content", Filter.class);
        assertThat(filters, not(empty()));
    }

    @Test
    public void error404OnGetAllProjectFiltersTest() {
        given().spec(requestSpecification)
                .when()
                .get("filters")
                .then()
                .statusCode(404)
                .body("error", equalTo("Not Found"));
    }

    @Test
    public void createFilterSuccessTest() {
        var filter = new Filter();
        filter.setName("New filter");
        filter.setType("launch");
        filter.setOrders(new ArrayList<>(Collections.singletonList(new Order(true, "number"))));
        filter.setConditions(new ArrayList<>(Collections.singletonList(new Condition("cnt", "name", "Lollipop"))));
        given().spec(requestSpecification)
                .header("Content-Type", "application/json")
                .body(filter)
                .when()
                .post("filter")
                .then()
                .statusCode(201)
                .assertThat()
                .body("id", notNullValue());
    }

    @Test
    public void createFilterWithMissingNameTest() {
        var filter = new Filter();
        filter.setName("");
        filter.setType("launch");
        filter.setOrders(new ArrayList<>(Collections.singletonList(new Order(true, "number"))));
        filter.setConditions(new ArrayList<>(Collections.singletonList(new Condition("cnt", "name", "Lollipop"))));
        given().spec(requestSpecification)
                .header("Content-Type", "application/json")
                .body(filter)
                .when()
                .post("filter")
                .then()
                .statusCode(400)
                .body("errorCode", equalTo(4001))
                .body(containsString(EMPTY_NAME_ERROR_MESSAGE));
    }

    @Test
    public void noRequestBodyOnPostFilter400ErrorTest() {
        given().spec(requestSpecification)
                .header("Content-Type", "application/json")
                .when()
                .post("filter")
                .then()
                .statusCode(400)
                .assertThat()
                .body("errorCode", equalTo(4001))
                .body(containsString("Incorrect Request. Required request body is missing"));
    }

    @Test
    public void updateFilterByIdTest() {
        var filter = new Filter();
        filter.setName("For update filter");
        filter.setType("launch");
        filter.setOrders(new ArrayList<>(Collections.singletonList(new Order(true, "number"))));
        filter.setConditions(new ArrayList<>(Collections.singletonList(new Condition("cnt", "name", "Lollipop"))));
        var responseJson = given().spec(requestSpecification)
                .header("Content-Type", "application/json")
                .body(filter)
                .post("filter")
                .as(JsonNode.class);
        var id = responseJson.get("id");

        var newName = "Changed filter";
        filter.setName(newName);
        given().spec(requestSpecification)
                .header("Content-Type", "application/json")
                .pathParam("filterId", id)
                .body(filter)
                .when()
                .put("filter/{filterId}")
                .then()
                .statusCode(200);

        given().spec(requestSpecification)
                .header("Content-Type", "application/json")
                .pathParam("filterId", id)
                .when()
                .get("filter/{filterId}")
                .then()
                .statusCode(200)
                .body("name", equalTo(newName));
    }

    @Test
    public void updateFilterWithInvalidIdTest() {
        var filter = new Filter();
        filter.setName("Any name");
        filter.setType("launch");
        filter.setOrders(new ArrayList<>(Collections.singletonList(new Order(true, "number"))));
        filter.setConditions(new ArrayList<>(Collections.singletonList(new Condition("cnt", "name", "Lollipop"))));
        given().spec(requestSpecification)
                .contentType(ContentType.JSON)
                .pathParam("filterId", 0)
                .body(filter)
                .when()
                .put("filter/{filterId}")
                .then()
                .statusCode(404);
    }

    @Test
    public void updateFilterWithMissingNameTest() {
        var filter = new Filter();
        filter.setName("For update with missing name filter");
        filter.setType("launch");
        filter.setOrders(new ArrayList<>(Collections.singletonList(new Order(true, "number"))));
        filter.setConditions(new ArrayList<>(Collections.singletonList(new Condition("cnt", "name", "Lollipop"))));
        var responseJson = given().spec(requestSpecification)
                .header("Content-Type", "application/json")
                .body(filter)
                .post("filter")
                .as(JsonNode.class);
        var id = responseJson.get("id");

        filter.setName("");
        given().spec(requestSpecification)
                .header("Content-Type", "application/json")
                .pathParam("filterId", id)
                .body(filter)
                .when()
                .put("filter/{filterId}")
                .then()
                .statusCode(400)
                .body("errorCode", equalTo(4001))
                .body(containsString(EMPTY_NAME_ERROR_MESSAGE));
    }

    @Test
    public void deleteFilterSuccessTest() {
        var filter = new Filter();
        filter.setName("For delete filter");
        filter.setType("launch");
        filter.setOrders(new ArrayList<>(Collections.singletonList(new Order(true, "number"))));
        filter.setConditions(new ArrayList<>(Collections.singletonList(new Condition("cnt", "name", "Lollipop"))));
        var responseJson = given().spec(requestSpecification)
                .header("Content-Type", "application/json")
                .body(filter)
                .post("filter")
                .as(JsonNode.class);
        var id = responseJson.get("id").asInt();

        var filterResponse = given().spec(requestSpecification)
                .pathParam("filterId", id)
                .when()
                .get("filter/{filterId}")
                .as(Filter.class);
        assertThat(filterResponse.getId(), equalTo(id));

        given().spec(requestSpecification)
                .pathParam("filterId", id)
                .when()
                .delete("filter/{filterId}")
                .then()
                .statusCode(200);

        given().spec(requestSpecification)
                .pathParam("filterId", id)
                .when()
                .get("filter/{filterId}")
                .then()
                .statusCode(404);
    }

    @Test
    public void deleteFilterWithInvalidIdTest() {
        given().spec(requestSpecification)
                .pathParam("filterId", 0)
                .when()
                .delete("filter/{filterId}")
                .then()
                .statusCode(404)
                .body(containsString(WRONG_ID_ERROR_MESSAGE));
    }
}
