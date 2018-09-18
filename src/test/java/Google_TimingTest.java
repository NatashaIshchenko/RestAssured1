import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Google_TimingTest {

    static String key;

    @BeforeClass
    public void  setup(){
        RestAssured.baseURI = Google_Base.BASE_URI;
        RestAssured.basePath = Google_Base.BASE_PATH;
        key = Google_Base.KEY;
    }

    @Test
    public void getTime() {
        long time_request =
                given()
                        .param("units", "imperial")
                        .param("origins", "Washington,DC")
                        .param("destinations", "New+York+City,NY")
                        .param("key", key)
                .when()
                        .get("/distancematrix/json")
                        .timeIn(TimeUnit.MILLISECONDS);
        System.out.println("time_request = " + time_request);
    }

    @Test
    public void checkTime(){
        given()
                .param("units", "imperial")
                .param("origins", "Washington,DC")
                .param("destinations", "New+York+City,NY")
                .param("key", key)
        .when()
                .get("/distancematrix/json")
        .then()
                .time(lessThan(1000L), TimeUnit.MILLISECONDS);
    }
}