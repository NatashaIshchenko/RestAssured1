import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.google.Example;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * GET https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=Washington,DC&destinations=New+York+City,NY&key=YOUR_API_KEY
 */
public class Google_GetRequestAndResponseTest {

    static String key;

    @BeforeClass
    public void  setup(){
        RestAssured.baseURI = Google_Base.BASE_URI;
        RestAssured.basePath = Google_Base.BASE_PATH;
        key = Google_Base.KEY;
    }

    @Test
    public void statusCodeVerification(){
        given()
                .param("units", "imperial")
                .param("origins", "Washington,DC")
                .param("destinations", "New+York+City,NY")
                .param("key", key).
        when()
                .get("/distancematrix/json").
        then()
                .statusCode(200);
    }

    /**
     * Осуществление отправки запроса с методом GET
     */
    private Response getFunc(){
        return
                given()
                        .param("units", "imperial")
                        .param("origins", "Washington,DC")
                        .queryParam("destinations", "New+York+City,NY") //можно использовать param и queryparam
                        .param("key", key)

                .when()
                        .get("/distancematrix/{type}", "json"); //используется параметр вместо get("/distancematrix/json")
    }

    @Test
    public void getResponseBody(){
        //System.out.println(get().body().asString());
        //get().body().prettyPrint(); // only body
        getFunc().body().prettyPeek(); // body + headers

    }

    @Test
    public void getHeadersAndCookies(){
        System.out.println("headers().getValue(Server) = " + getFunc().headers().getValue("Server"));
        System.out.println("cookies() = " + getFunc().cookies());
    }

    @Test
    public void responseAsJsonObject(){
        Response response = getFunc();
        JSONObject object = new JSONObject(response.asString());
        System.out.println("JSONObject object = " + object);
    }

    @Test
    public void validateResponseBody(){
        getFunc()
                .then()
                .statusCode(200)
                .body("rows[0].elements[0].distance.text", equalTo("225 mi"))
                .contentType(ContentType.JSON);
   }

   @Test
   public  void getFieldFromResponse(){
        Response response = getFunc();
        String origin_addresses = response.path("origin_addresses[0]");
        System.out.println("origin_addresses1 = " + origin_addresses);
   }

   @Test
   public void getFieldFromResponseUsingExtract(){
        Response response = getFunc()
                                .then()
                                .statusCode(200)
                                .extract().response();

        String origin_addresses = response.path("origin_addresses[0]");
        System.out.println("origin_addresses2 = " + origin_addresses);
   }

    @Test
    public void getFieldFromResponseUsingJsonPath(){
        Response response = getFunc()
                .then()
                .statusCode(200)
                .extract().response();

        String stringResponse = response.asString();
        JsonPath jsonPath = new JsonPath(stringResponse);

        String origin_addresses = jsonPath.get("origin_addresses[0]");
        System.out.println("origin_addresses3 = " + origin_addresses);

        long distanceValue = jsonPath.getLong("rows[0].elements[0].distance.value");
        System.out.println("distanceValue = " + distanceValue);

    }

    @Test
    public void getResponseAsClass(){
        Example example = getFunc().as(Example.class);
        System.out.println(example);
    }
}