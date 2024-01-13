import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ApiTests {
    @Test
    void successfulRegisterTest() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

        .when()
            .post("https://reqres.in/api/register")

        .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void unsuccessfulRegister400Test() {
        String regData = "";

        given()
                .body(regData)
                .log().uri()

        .when()
            .post("https://reqres.in/api/register")

        .then()
            .log().status()
            .log().body()
            .statusCode(400)
            .body("error", is("Missing email or username"));
    }


    @Test
    void missingPasswordTest() {
        String regData = "{\"email\": \"lala.holt@reqres.in\"}";

        given()
                .body(regData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }


    @Test
    void missingLoginTest() {
        String regData = "{\"password\": \"123\"}";

        given()
                .body(regData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }
    @Test
    void wrongBodyTest() {
        String regData = ".}";

        given()
                .body(regData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400);
    }

    @Test
    void unsuccessfulRegister415Test() {
        given()
                .log().uri()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);
    }
    @Test
    void listUserTest() {
            given()
                    .when()
                    .get("https://reqres.in/api/users?page=2")
                    .then()
                    .body("page", is(2));
        }

    @Test
    void createUserTest() {
        String creData = "{\"name\": \"morpheus\", \"job\": \"leader\"}" ;

        given()
                .body(creData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"));
    }

    }






