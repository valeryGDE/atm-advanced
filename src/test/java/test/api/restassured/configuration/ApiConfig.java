package test.api.restassured.configuration;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import main.java.ui.core.properties.EnvProperty;
import main.java.ui.core.properties.PropertyReader;

public interface ApiConfig {
    String BASE_URL = PropertyReader.getProperty(EnvProperty.LOCAL_BASE_URL.getKey());
    String ADMIN_PROJECT_NAME = PropertyReader.getProperty(EnvProperty.ADMIN_PROJECT_NAME.getKey());

    static RequestSpecification getDefaultSpec() {
        RestAssured.config = RestAssuredConfig.config();
        RestAssured.baseURI = BASE_URL + "/api/v1/" + ADMIN_PROJECT_NAME;
        return new RequestSpecBuilder().build()
                .redirects().follow(true)
                .header("Authorization", "Bearer " + System.getenv("RP_API_TOKEN"))
                .baseUri(RestAssured.baseURI)
                .filter(new RequestLoggingFilter(LogDetail.ALL))
                .filter(new ResponseLoggingFilter(LogDetail.ALL))
                .urlEncodingEnabled(false)
                .proxy(8866);
    }
}
