package sh.jfm.springbootdemos.problemdetails;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static net.javacrumbs.jsonunit.JsonMatchers.jsonEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StandardErrorTests {
    @LocalServerPort
    private int port;

    @BeforeAll
    static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void aMissingEndpointReturnsAStandardError() {
        given()
                .port(port)
                .when()
                .get("/this-endpoint-does-not-exist")
                .then()
                .statusCode(404)
                .body(jsonEquals(("""
                        {
                          "timestamp": "ignored",
                          "status": 404,
                          "error": "Not Found",
                          "path": "/this-endpoint-does-not-exist"
                        }""")).whenIgnoringPaths("timestamp")
                );
    }
}
