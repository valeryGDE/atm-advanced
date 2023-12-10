package test.api.restassured;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
import test.api.restassured.configuration.ApiConfig;

public class BaseApiTest {
    protected RequestSpecification requestSpecification;

    @BeforeSuite
    public void setUpApiEnvironment() {
        requestSpecification = ApiConfig.getDefaultSpec();
    }
}
