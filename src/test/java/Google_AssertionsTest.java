import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class Google_AssertionsTest {

    static String key;

    @BeforeClass
    public void  setup(){
        RestAssured.baseURI = Google_Base.BASE_URI;
        RestAssured.basePath = Google_Base.BASE_PATH;
        key = Google_Base.KEY;
    }

    @Test
    public void hardAssertionsFunc(){
        given()
                .param("units", "imperial")
                .param("origins", "Washington,DC")
                .param("destinations", "New+York+City,NY")
                .param("key", key)
        .when()
                .get("/distancematrix/json")
        .then()
                .rootPath("rows[0].elements[0]")
                .body("distance.text", equalTo("225 mi"))
                .body("size()", equalTo(3))
                .body("", hasKey("status"))
                .extract().response().prettyPrint();
    }

    //@Test
    public void softAssertionsFunc(){
        given()
                .param("units", "imperial")
                .param("origins", "Washington,DC")
                .param("destinations", "New+York+City,NY")
                .param("key", key)
                .when()
                .get("/distancematrix/json")
                .then()
                .body(       "rows[0].elements[0].distance.text", equalTo("225 mi"),
                        "rows[0].elements[0].size()", equalTo(3),
                                "rows[0].elements[0]", hasKey("status"))
                .extract().response().prettyPrint();
    }
}