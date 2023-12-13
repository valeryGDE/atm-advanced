package test.api.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BaseHttpClientTest {
    protected CloseableHttpClient httpClient;
    protected RequestBuilder requestBuilder;

    @BeforeSuite
    public void setUpHttpClientEnvironment() {
        httpClient = HttpClientConfig.getDefaultHttpClient();
    }

    @AfterSuite
    public void tearDownHttpClient() {
        HttpClientConfig.closeHttpClient(httpClient);
    }

    protected CloseableHttpResponse executeGetRequest(String endpoint) throws IOException {
        requestBuilder = HttpClientConfig.getDefaultRequestBuilder(endpoint, "GET");
        return httpClient.execute(requestBuilder.build());
    }

    protected CloseableHttpResponse executePostRequest(String endpoint, String body) throws IOException {
        requestBuilder = HttpClientConfig.getDefaultRequestBuilder(endpoint, "POST");
        requestBuilder.addHeader("Content-Type", "application/json");
        requestBuilder.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        return httpClient.execute(requestBuilder.build());
    }

    protected CloseableHttpResponse executePutRequest(String endpoint, String body) throws IOException {
        requestBuilder = HttpClientConfig.getDefaultRequestBuilder(endpoint, "PUT");
        requestBuilder.addHeader("Content-Type", "application/json");
        requestBuilder.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        return httpClient.execute(requestBuilder.build());
    }

    protected CloseableHttpResponse executeDeleteRequest(String endpoint) throws IOException {
        requestBuilder = HttpClientConfig.getDefaultRequestBuilder(endpoint, "DELETE");
        return httpClient.execute(requestBuilder.build());
    }
}
