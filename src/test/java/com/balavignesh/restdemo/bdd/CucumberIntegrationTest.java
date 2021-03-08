package com.balavignesh.restdemo.bdd;

import com.balavignesh.restdemo.RestCucumberApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@CucumberContextConfiguration
@SpringBootTest(classes = RestCucumberApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class CucumberIntegrationTest {

    private  static  final  String  BASE_URL  =  "http://localhost:";

    @Autowired
    protected TestRestTemplate restTemplate;

    @LocalServerPort
    protected int serverPort;

    public HttpHeaders getDefaultHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public String buildUrl( String apiPath,String value) {

        return new StringBuffer().append(buildBaseUrl()).append(apiPath).append("/").append(value).toString();
    }

    public String buildUrl( String path) {

        return new StringBuffer().append(buildBaseUrl()).append(path).toString();
    }

    public String buildBaseUrl() {

        return new StringBuffer().append(BASE_URL).append(serverPort).toString();
    }

}
