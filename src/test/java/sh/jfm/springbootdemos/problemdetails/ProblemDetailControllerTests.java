package sh.jfm.springbootdemos.problemdetails;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static net.javacrumbs.jsonunit.JsonMatchers.jsonEquals;
import static org.hamcrest.Matchers.equalTo;

/**
 * Test manual Problem Details creating through controller advice
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProblemDetailControllerTests {
    @LocalServerPort
    private int port;

    @BeforeAll
    static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void returnsASimpleMessageOnPositiveNumbers() {
        given()
                .port(port)
                .when()
                .get("/sample-endpoint/1")
                .then()
                .statusCode(200)
                .body(equalTo("1 is positive"));
    }

    @Test
    void returnsAProblemDetailOnNegativeNumbers() {
        given()
                .port(port)
                .when()
                .get("/sample-endpoint/-1")
                .then()
                .statusCode(400)
                .body(jsonEquals(("""
                        {
                          "type": "about:blank",
                          "title": "Bad Request",
                          "status": 400,
                          "detail": "num must be more zero",
                          "instance": "/sample-endpoint/-1"
                        }"""))
                );
    }
}
