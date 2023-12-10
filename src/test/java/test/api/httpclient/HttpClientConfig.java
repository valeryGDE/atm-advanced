package test.api.httpclient;

import main.java.ui.core.properties.EnvProperty;
import main.java.ui.core.properties.PropertyReader;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public interface HttpClientConfig {
    String BASE_URL = PropertyReader.getProperty(EnvProperty.LOCAL_BASE_URL.getKey());
    String ADMIN_PROJECT_NAME = PropertyReader.getProperty(EnvProperty.ADMIN_PROJECT_NAME.getKey());

    static CloseableHttpClient getDefaultHttpClient() {
        return HttpClients.createDefault();
    }

    static RequestBuilder getDefaultRequestBuilder(String endpoint, String httpMethod) {
        return RequestBuilder
                .create(httpMethod)
                .setUri(BASE_URL + "api/v1/" + ADMIN_PROJECT_NAME + "/" + endpoint)
                .setHeader("Authorization", "Bearer " + System.getenv("RP_API_TOKEN"))
                .setConfig(RequestConfig.custom().setProxy(new HttpHost("localhost", 8866)).build());
    }

    static void closeHttpClient(CloseableHttpClient httpClient) {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
