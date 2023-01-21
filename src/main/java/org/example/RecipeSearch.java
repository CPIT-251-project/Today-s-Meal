package org.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeSearch {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter ingredients to search: ");
        String ingredients = scan.nextLine();
        try {
            URL url = new URL("https://api.spoonacular.com/recipes/findByIngredients?ingredients=" + ingredients + "&apiKey=" + "b1270e9e454342f182aee416420012a0");
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
            
            JSONArray results = new JSONArray(content.toString());
            for (int i = 0; i < results.length(); i++) {
                JSONObject recipe = results.getJSONObject(i);
                System.out.println("Title: " + recipe.getString("title"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
