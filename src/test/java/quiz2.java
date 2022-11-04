import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class quiz2 {

    @Test
    public void checkNonePass(){
        int statusCode=
                given()
                        .contentType(ContentType.JSON)
                        .body("{\n" +
                                "  \"userName\": \"anzo123\",\n" +
                                "  \"password\": \"\"\n" +
                                "}")
                        .when().post("https://bookstore.toolsqa.com/Account/v1/User").getStatusCode();
        Assert.assertEquals(statusCode,400);

    }

    @Test
    public void checkNumberPass(){
        String statusCode=
                given()
                        .contentType(ContentType.JSON)
                        .body("{\n" +
                                "  \"userName\": \"anzo123\",\n" +
                                "  \"password\": \"11111111\"\n" +
                                "}")
                        .when().post("https://bookstore.toolsqa.com/Account/v1/User")
                        .then()
        .extract()
                .jsonPath().getString("code");
        Assert.assertEquals(statusCode, "1300");
    }

    @Test
    public void checkCorrectPass(){
        String username = RandomStringUtils.randomAlphabetic(10);
        int statusCode=
                given()
                        .contentType(ContentType.JSON)
                        .body("{\n" +
                                "  \"userName\": \""+username+"\",\n" +
                                "  \"password\": \"Btu12345!\"\n" +
                                "}")
                        .when().post("https://bookstore.toolsqa.com/Account/v1/User").getStatusCode();
        Assert.assertEquals(statusCode,201);
    }

}
