package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeSearch {

        public static void main(String[] args) throws Exception {
            Scanner scan = new Scanner(System.in);
            while (true) {
                try{
                    display(scan);
                }catch (Exception e){
                    System.out.println("Error: " + e.getMessage());
                    break;
                }
            }
        }

    public static void display(Scanner scan) {
        System.out.println("hi");
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("---------------------Welcome to Today's Meal---------------------");
        System.out.println("Enter your choice: ");
        System.out.println("1. Search recipes");
        System.out.println("2. Exit");
        int choice = scan.nextInt();

        try{
        switch (choice) {
            case 1:
                System.out.println("Enter ingredients to search: ");
                scan.nextLine();
                String ingredients = scan.nextLine();

                try {
                    search(scan, ingredients);

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            case 2:
                System.out.println("Exiting Today's Meal. Have a great day!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }}catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void search(Scanner scan, String ingredients) throws IOException {
        JSONArray results = getRecipe(ingredients);

        for (int i = 0; i < results.length(); i++) {
            JSONObject recipe = results.getJSONObject(i);
            System.out.println(i + 1 + "- Title: " + recipe.getString("title"));
        }

        System.out.println("Enter recipe number to view instructions: ");
        int recipeNum = scan.nextInt();

        JSONObject recipe = results.getJSONObject(recipeNum - 1);
        System.out.println("Instructions: " + getInstructions(recipe.getInt("id")));
        System.out.println("Calories: " + getCalories(recipe.getInt("id")));
        System.out.println("Thank you for using Today's Meal");
        System.exit(0);
    }
    public static JSONArray getRecipe(String ingredients) throws IOException {

        URL url = new URL("https://api.spoonacular.com/recipes/findByIngredients?ingredients=" + ingredients
                + "&apiKey=" + "b1270e9e454342f182aee416420012a0");
        StringBuilder content = getContent(url);

        JSONArray results = new JSONArray(content.toString());
        return results;
    }
    public static String getInstructions(int recipeId) throws IOException {
        URL url = new URL("https://api.spoonacular.com/recipes/" + recipeId + "/analyzedInstructions?apiKey=" + "b1270e9e454342f182aee416420012a0");
        StringBuilder content = getContent(url);

        JSONArray instructionsArray = new JSONArray(content.toString());
        StringBuilder instructions = new StringBuilder();
        for (int i = 0; i < instructionsArray.length(); i++) {
            JSONObject instruction = instructionsArray.getJSONObject(i);
            JSONArray steps = instruction.getJSONArray("steps");
            for (int j = 0; j < steps.length(); j++) {
                JSONObject step = steps.getJSONObject(j);
                instructions.append((j + 1) + ". " + step.getString("step") + "\n");
            }
        }
        return instructions.toString();
    }
    public static String getCalories(int recipeId) throws IOException {
        URL url = new URL("https://api.spoonacular.com/recipes/" + recipeId + "/nutritionWidget.json?apiKey=" + "b1270e9e454342f182aee416420012a0");
        StringBuilder content = getContent(url);

        JSONObject recipeInfo = new JSONObject(content.toString());
        String calories = recipeInfo.getString("calories");
        return calories;
    }
    private static StringBuilder getContent(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String Line;
        StringBuilder content = new StringBuilder();
        while ((Line = in.readLine()) != null) {
            content.append(Line);
        }
        in.close();
        con.disconnect();
        return content;
    }
}