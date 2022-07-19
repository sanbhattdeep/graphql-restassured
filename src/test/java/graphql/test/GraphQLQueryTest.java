package graphql.test;

import io.restassured.http.ContentType;
import model.GraphQLQuery;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.hasSize;

import static io.restassured.RestAssured.given;

public class GraphQLQueryTest {

    @Test
    public void testALlFilmsQuery() {

        GraphQLQuery query = new GraphQLQuery();
        String jsonString = query.graphqlToJson("{\n" +
                "  allFilms {\n" +
                "    films {\n" +
                "      title,\n" +
                "      releaseDate\n" +
                "    }\n" +
                "  }\n" +
                "}");
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .baseUri("https://swapi-graphql.netlify.app")
                .body(jsonString)
                .when().log().all()
                .post("/.netlify/functions/index")
                .then().log().all()
                .statusCode(200)
                .and()
                .body("data.allFilms.films",hasSize(5));

    }
}
