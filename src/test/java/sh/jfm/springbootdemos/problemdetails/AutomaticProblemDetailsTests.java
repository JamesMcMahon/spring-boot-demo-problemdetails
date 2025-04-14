package sh.jfm.springbootdemos.problemdetails;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static net.javacrumbs.jsonunit.JsonMatchers.jsonEquals;

/**
 * Tests automatic conversion of standard Spring Boot errors through app configuration
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.mvc.problemdetails.enabled=true"}
)
public class AutomaticProblemDetailsTests {
    @LocalServerPort
    private int port;

    @BeforeAll
    static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void aMissingEndpointReturnsAProblemDetail() {
        given()
                .port(port)
                .when()
                .get("/this-endpoint-does-not-exist")
                .then()
                .statusCode(404)
                .body(jsonEquals(("""
                        {
                          "type": "about:blank",
                          "title": "Not Found",
                          "status": 404,
                          "detail": "No static resource this-endpoint-does-not-exist.",
                          "instance": "/this-endpoint-does-not-exist"
                        }"""))
                );
    }
}
