package org.example;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class RecipeSearchTest {
    private final RecipeSearch Rs = new RecipeSearch();

    @Test
    void shouldReturnEggResults() throws IOException {
        JSONArray results = RecipeSearch.getRecipe("egg");
        JSONAssert.assertEquals(results, Rs.getRecipe("egg"), true);
        //assertEquals(results.toString() , Rs.getRecipe("egg").toString());
    }
    @Test
    void shouldReturnInstructions() throws IOException {
        JSONArray results = RecipeSearch.getRecipe("egg");
        JSONObject recipe = results.getJSONObject(1);
        assertEquals(RecipeSearch.getInstructions(1),Rs.getInstructions(1));
        //JSONAssert.assertEquals(results, Rs.getRecipe("egg"), true);
    }
}