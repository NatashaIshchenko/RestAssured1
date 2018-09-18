import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import model.bugred.UserAddModel;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;


public class Bugred_PostRequestTest {

    /**
     * request specification для RestAssured
     */
    protected static RequestSpecification spec = null;
    private static String baseURI = "http://users.bugred.ru";
    private static String basePath = "/tasks/rest";

    @BeforeClass
    public void specInitialization(){
        if (spec == null) {
            spec = new RequestSpecBuilder().build().baseUri(baseURI).basePath(basePath);
        }
    }

    @Test
    private void postWithJson() {
        //генерирование данных на создание игрока
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String email = RandomStringUtils.randomAlphabetic(10).concat("@test.ttt");
        String body = "{\n" +
                "    \"email\": \"" + email + "\",\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"password\": \"testpass\"\n" +
                "}";

        //отправка запроса на создание игрока
        RequestSpecification finalSpec = given().spec(spec);
        //parameters.forEach((key, value) -> finalSpec.queryParam(key, value)); - так можно докинуть еще параметров (parameters is Map)
        finalSpec
                .body(body)
        .when()
                .post("/doregister")
                .body()
                .prettyPrint();
    }

    @Test
    private void postWithClass() {
        //генерирование данных на создание игрока
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String email = RandomStringUtils.randomAlphabetic(10).concat("@test.ttt");
        UserAddModel userAddModel = new UserAddModel();
        userAddModel.setEmail(email);
        userAddModel.setName(name);
        userAddModel.setPassword("testpass");

        //отправка запроса на создание игрока
        given()
                .spec(spec)
                .body(userAddModel)
        .when()
                .post("/doregister")
                .body()
                .prettyPrint();
    }
}
