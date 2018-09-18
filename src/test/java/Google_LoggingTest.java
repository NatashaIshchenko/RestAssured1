import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Google_LoggingTest {

    static String key;

    @BeforeClass
    public void  setup(){
        RestAssured.baseURI = Google_Base.BASE_URI;
        RestAssured.basePath = Google_Base.BASE_PATH;
        key = Google_Base.KEY;
    }

    @Test
    public void requestLogging() {
        given()
                .log()
                //.headers()
                //.body()
                //.parameters()
                //.all()
                .ifValidationFails()
                .param("units", "imperial")
                .param("origins", "Washington,DC")
                .param("destinations", "New+York+City,NY")
                .param("key", key)
        .when()
                .get("/distancematrix/json")
        .then()
                .statusCode(200);
    }

    @Test
    public void responseLogging() {
        given()
                .param("units", "imperial")
                .param("origins", "Washington,DC")
                .param("destinations", "New+York+City,NY")
                .param("key", key)
        .when()
                .get("/distancematrix/json")
        .then()
                .log()
                //.headers()
                //.body()
                //.all()
                //.ifValidationFails()
                .ifError()
                .statusCode(200);
    }
}
