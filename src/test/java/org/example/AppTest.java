package org.example;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.*;
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

    @Test
    void shouldHandleInvalidUserInput() {
        String data = "3\n2\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scan = new Scanner(System.in);
        RecipeSearch.display(scan);
    }

    @Test
    void shouldDisplayErrorForInvalidRecipeNumber() {
        String data = "1\negg\n3\n2\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scan = new Scanner(System.in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        RecipeSearch.display(scan);

        String expectedOutput = "1. Search recipes\n2. Exit\nEnter ingredients to search: \nEnter recipe number to view instructions: \nError: Index 2 out of bounds for length 1\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}