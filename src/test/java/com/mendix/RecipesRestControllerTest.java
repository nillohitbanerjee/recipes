package com.mendix;


import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
@TestPropertySource(value={"classpath:application.properties"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecipesRestControllerTest {


    @Value("${server.port}")
    int port;
    @Test
    public void checkEmptyDataResponseCodeForCategory() {
        get("/services/recipe/filter/all").then().assertThat().statusCode(204);

    }

    @Test
    public void checkEmptyDataResponseCodeForRecipe() {
        get("/services/recipe/test").then().assertThat().statusCode(204);

    }

    @Test
    public void checkSaveForCategory() {
        given()
                .contentType("application/json").
                        body("cake").
                        when().
                        post("/services/recipe/category").then().assertThat().statusCode(201);

        get("/services/recipe/filter/1").then().assertThat().statusCode(200);

        get("/services/recipe/filter/cake").then().assertThat().statusCode(200);

        given()
                .contentType("application/json").
                body("cake").
                when().
                post("/services/recipe/category").then().assertThat().statusCode(409);
    }

    @Test
    public void checkSaveForCategoryThenCheckEmptyDataResponseCodeForCategory() {
        get("/services/recipe/filter/all").then().assertThat().statusCode(200);

    }


    @Test
    public void checkSaveForRecipesThenTestAll() {
        given()
                .contentType("application/json").
                body("cola").
                when().
                post("/services/recipe/category");

        given().contentType("application/json").
                body("{\n" +
                        "      \"head\": {\n" +
                        "        \"title\": \"40 Minute special\",\n" +
                        "        \"categories\": {\n" +
                        "          \"cat\": [\n" +
                        "\n" +
                        "        {\n" +
                        "          \"categoryName\": \"cola\",\n" +
                        "          \"id\": 0\n" +
                        "        }\n" +
                        "          ]\n" +
                        "        },\n" +
                        "        \"yield\": \"6\"\n" +
                        "      },\n" +
                        "      \"ingredients\": {\n" +
                        "      \t\"ingdiv\": [\n" +
                        "      {\n" +
                        "        \"title\": \"\",\n" +
                        "        \"ing\": [\n" +
                        "          {\n" +
                        "            \"amt\": {\n" +
                        "              \"qty\": \"1\",\n" +
                        "              \"unit\": \"pound\"\n" +
                        "            },\n" +
                        "            \"item\": \"Ground chuck or lean ground; beef\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"amt\": {\n" +
                        "              \"qty\": \"1\",\n" +
                        "              \"unit\": \"\"\n" +
                        "            },\n" +
                        "            \"item\": \"Onion; large, chopped\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"amt\": {\n" +
                        "              \"qty\": \"1\",\n" +
                        "              \"unit\": \"can\"\n" +
                        "            },\n" +
                        "            \"item\": \"Kidney beans; (12 oz)\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"amt\": {\n" +
                        "              \"qty\": \"1\",\n" +
                        "              \"unit\": \"can\"\n" +
                        "            },\n" +
                        "            \"item\": \"Tomato soup; undiluted\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"amt\": {\n" +
                        "              \"qty\": \"1\",\n" +
                        "              \"unit\": \"teaspoon\"\n" +
                        "            },\n" +
                        "            \"item\": \"Salt\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"amt\": {\n" +
                        "              \"qty\": \"1\",\n" +
                        "              \"unit\": \"tablespoon\"\n" +
                        "            },\n" +
                        "            \"item\": \"Chili powder; (or to taste)\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"amt\": {\n" +
                        "              \"qty\": \"\",\n" +
                        "              \"unit\": \"\"\n" +
                        "            },\n" +
                        "            \"item\": \"Hot pepper sauce; to taste\"\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      }]},\n" +
                        "      \"directions\": {\n" +
                        "        \"step\": \"  Brown the meat in a little butter and cook until the meat is brown -- about\\n  10 minutes. Add all other ingredients and let simmer for 30 minutes. Your\\n  choice of hot sauce may be added to taste.\\n  \\n  Recipe by: MasterCook Archives\\n  \\n  Posted to recipelu-digest Volume 01 Number 577 by Rodeo46898\\n  <Rodeo46898@aol.com> on Jan 22, 1998\\n \\n\"\n" +
                        "      }\n" +
                        "    }\n").when().
                put("/services/recipe/add").then().assertThat().statusCode(201);


        given().contentType("application/json").
                body("{\n" +
                        "      \"head\": {\n" +
                        "        \"title\": \"40 Minute special\",\n" +
                        "        \"categories\": {\n" +
                        "          \"cat\": [\n" +
                        "\n" +
                        "        {\n" +
                        "          \"categoryName\": \"cola\",\n" +
                        "          \"id\": 0\n" +
                        "        }\n" +
                        "          ]\n" +
                        "        },\n" +
                        "        \"yield\": \"6\"\n" +
                        "      },\n" +
                        "      \"ingredients\": {\n" +
                        "      \t\"ingdiv\": [\n" +
                        "      {\n" +
                        "        \"title\": \"\",\n" +
                        "        \"ing\": [\n" +
                        "          {\n" +
                        "            \"amt\": {\n" +
                        "              \"qty\": \"1\",\n" +
                        "              \"unit\": \"pound\"\n" +
                        "            },\n" +
                        "            \"item\": \"Ground chuck or lean ground; beef\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"amt\": {\n" +
                        "              \"qty\": \"1\",\n" +
                        "              \"unit\": \"\"\n" +
                        "            },\n" +
                        "            \"item\": \"Onion; large, chopped\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"amt\": {\n" +
                        "              \"qty\": \"1\",\n" +
                        "              \"unit\": \"can\"\n" +
                        "            },\n" +
                        "            \"item\": \"Kidney beans; (12 oz)\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"amt\": {\n" +
                        "              \"qty\": \"1\",\n" +
                        "              \"unit\": \"can\"\n" +
                        "            },\n" +
                        "            \"item\": \"Tomato soup; undiluted\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"amt\": {\n" +
                        "              \"qty\": \"1\",\n" +
                        "              \"unit\": \"teaspoon\"\n" +
                        "            },\n" +
                        "            \"item\": \"Salt\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"amt\": {\n" +
                        "              \"qty\": \"1\",\n" +
                        "              \"unit\": \"tablespoon\"\n" +
                        "            },\n" +
                        "            \"item\": \"Chili powder; (or to taste)\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"amt\": {\n" +
                        "              \"qty\": \"\",\n" +
                        "              \"unit\": \"\"\n" +
                        "            },\n" +
                        "            \"item\": \"Hot pepper sauce; to taste\"\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      }]},\n" +
                        "      \"directions\": {\n" +
                        "        \"step\": \"  Brown the meat in a little butter and cook until the meat is brown -- about\\n  10 minutes. Add all other ingredients and let simmer for 30 minutes. Your\\n  choice of hot sauce may be added to taste.\\n  \\n  Recipe by: MasterCook Archives\\n  \\n  Posted to recipelu-digest Volume 01 Number 577 by Rodeo46898\\n  <Rodeo46898@aol.com> on Jan 22, 1998\\n \\n\"\n" +
                        "      }\n" +
                        "    }\n").when().
                put("/services/recipe/add").then().assertThat().statusCode(409);

        get("/services/recipe/cola").then().assertThat().statusCode(200);
        get("/services/recipe/2").then().assertThat().statusCode(200);
        get("/services/recipe/2/3").then().assertThat().statusCode(200);
        get("/services/recipe/all").then().assertThat().statusCode(200);
        get("/services/recipe/cola/3").then().assertThat().statusCode(200);
        get("/services/recipe/xyz/3").then().assertThat().statusCode(204);
        get("/services/recipe/cola/20").then().assertThat().statusCode(204);
        get("/services/recipe/cola/abc").then().assertThat().statusCode(204);

    }
    @Before
    public void setBaseUri () {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost"; // replace as appropriate
    }
}
