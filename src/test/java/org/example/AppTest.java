package org.example;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RecipeSearchTest {
    private final RecipeSearch Rs = new RecipeSearch();

    @Test
    void shouldReturnEggResults() throws IOException {
        JSONArray results = RecipeSearch.getRecipe("egg");
        JSONAssert.assertEquals(results, Rs.getRecipe("egg"), true);
    }
    @Test
    void shouldReturnInstructions() throws IOException {
        assertEquals(RecipeSearch.getInstructions(1),Rs.getInstructions(1));
    }
    @Test
    void shouldReturnCal() throws IOException {
        assertEquals(RecipeSearch.getCalories(1),Rs.getCalories(1));
    }
    @Test
    void shouldReturnException() throws IOException, Exception {
        String data = "a";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scan = new Scanner(System.in);

        assertThrows(Exception.class, () ->{RecipeSearch.display(scan);});
        assertThrows(Exception.class, () ->{RecipeSearch.search(scan, "egg");});
    }
}