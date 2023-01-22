package org.example;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class RecipeSearchTest {
    private final RecipeSearch Rs = new RecipeSearch();

    @Test
    void shouldReturnEggResults() throws IOException {
        JSONArray results = RecipeSearch.getRecipe("egg");

        assertEquals(results.toString() , Rs.getRecipe("egg").toString());
    }
}