import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Google_SpecificationTest {

    RequestSpecBuilder requestBuilder;
    static RequestSpecification requestSpec;

    ResponseSpecBuilder responseBuilder;
    static ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {

        //Request specification
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(Google_Base.BASE_URI);
        requestBuilder.setBasePath(Google_Base.BASE_PATH);
        requestBuilder.addQueryParam("key", Google_Base.KEY);
        requestSpec = requestBuilder.build();

        //Response specification
        responseBuilder = new ResponseSpecBuilder();
        responseBuilder.expectStatusCode(200);
        responseBuilder.expectResponseTime(lessThan(2L), TimeUnit.SECONDS);
        responseBuilder.expectBody("rows[0].elements[0].distance.text", equalTo("225 mi"));
        responseSpec = responseBuilder.build();
    }

    @Test
    public void requestAndResponseSpecFunc(){
        given()
                .spec(requestSpec)
                .param("units", "imperial")
                .param("origins", "Washington,DC")
                .param("destinations", "New+York+City,NY")
        .when()
                .get("/distancematrix/json")
        .then()
                .spec(responseSpec)
                .extract().response().prettyPrint();
    }
}
