import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ApiTests {
    @Test
    void successfulRegisterTest() {
        String RegData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

        given()
                .body(RegData)
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
                    .body("page", is(2))
                            .log().status()
                            .log().body();
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
    @Test
    void updateUserTest() {
        String updData = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}" ;

        given()
                .body(updData)
                .contentType(JSON)
                .log().uri()

                .when()
                .put("https://reqres.in/api/users/2")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"));
    }
    @Test
    void deleteUserTest() {

        given()
                .contentType(JSON)
                .log().uri()

                .when()
                .delete("https://reqres.in/api/users/2")

                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    void listResoursesTest() {
        given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .body("page", is(1))
                .log().status()
                .log().body();
    }
    @Test
    void delayUserTest() {
        given()
                .when()
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .body("page", is(1))
                .log().status()
                .log().body();
    }
    }







