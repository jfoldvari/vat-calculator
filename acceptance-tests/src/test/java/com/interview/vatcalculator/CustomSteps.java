package com.interview.vatcalculator;

import com.interview.vatcalculator.application.VatCalculatorApplication;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(classes = VatCalculatorApplication.class)
@CucumberContextConfiguration
public class CustomSteps {
    ResponseEntity<String> lastResponse;

    @When("the client calls endpoint {string}")
    public void whenClientCalls(String url) {
        try {
            lastResponse = new RestTemplate().exchange("http://localhost:8080" + url, HttpMethod.GET, null,
                    String.class);
        } catch (HttpClientErrorException httpClientErrorException) {
            httpClientErrorException.printStackTrace();
        }
    }

    @Then("response status code is {int}")
    public void thenStatusCode(Integer expected) {
        assertThat("status code is" + expected,
                expected.equals(lastResponse.getStatusCodeValue()));
    }

    @Then("returned string should be {string}")
    public void thenStringIs(String expected) {
        Assertions.assertEquals(expected, lastResponse.getBody());
    }

}
