package graphql.test;

import io.restassured.http.ContentType;
import model.GraphQLQuery;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class GraphQLDataTest {

    @DataProvider
    public Object[][] getQueryData() {
       return new Object[][] {{"10"},{"5"}};
    }

    @Test(dataProvider = "getQueryData")
    public void testAllUsersQuery(String limit) {

        String query = "{\n" +
                "  users(limit: "+limit+") {\n" +
                "    id\n" +
                "    name\n" +
                "  }\n" +
                "}";

        given()
                .log().all()
                .baseUri("https://hasura.io/")
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYyZDUwNDcyYmIzMjA0YmQzN2EyMGEzNyJ9LCJuaWNrbmFtZSI6InNhbmRlZXBpa2EubS4yMDQiLCJuYW1lIjoic2FuZGVlcGlrYS5tLjIwNEBnbWFpbC5jb20iLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvY2YzNmNlNWY5Y2NkNGRhNGZiM2Q0NDg0ZTVkZGVkNjg_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZzYS5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMi0wNy0xOFQwNjo1Nzo1NS4zNjlaIiwiaXNzIjoiaHR0cHM6Ly9ncmFwaHFsLXR1dG9yaWFscy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjJkNTA0NzJiYjMyMDRiZDM3YTIwYTM3IiwiYXVkIjoiUDM4cW5GbzFsRkFRSnJ6a3VuLS13RXpxbGpWTkdjV1ciLCJpYXQiOjE2NTgxMjc0NzYsImV4cCI6MTY1ODE2MzQ3NiwiYXRfaGFzaCI6IlptaWw0OWFScm44ZU1La2xSVDM3elEiLCJub25jZSI6IkRnWGJrcWN-SjB5OXRSM2RRS0l6T2hVN0dOc1RGRGdKIn0.i9K_Wq0-0c-Wrk3vlVrJds5I6pVf2RQ2VBIq3doMXTXMzeCF-brucriEwP9wpQ4oEL_YWM7iyJcmEdaHrADjxOhxnLyx7prLsIN_t1AHe7e_7S_ZAJ5e7JxXnKt7QpEiRMVll6jb3eCFucF8l5-aZboVoIzLopc-eah0A3j1pH2MsfFix4KdK7fBpmHnzErMxwIHVQ7ffKe6AoH4Yzkk6LALsh-bkR3mzWrBqBeRgocS7dmzokKj22BO2Wxk8hlIz7rIw6D6ypomHjdXCtK39A4nFxeSvb3YTz5DzlQszFR9QsboL7u3SNl2EdSUPCrqZfroafw9Bj0J6kVNT57oGw")
                .body(GraphQLQuery.graphqlToJson(query))
                .when().log().all()
                .post("learn/graphql")
                .then().log().all()
                .statusCode(200)
                .body("data.users",hasSize(Integer.parseInt(limit)));


    }
}
