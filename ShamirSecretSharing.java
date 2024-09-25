import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShamirSecretSharing {
    
    public static void main(String[] args) {
        // Read the JSON input from a file
        String filePath = "input.json"; // Change this to your file path
        JSONObject jsonInput = readJsonFile(filePath);
        
        if (jsonInput == null) {
            System.out.println("Failed to read JSON input.");
            return;
        }

        // Extract n and k values
        JSONObject keys = jsonInput.getJSONObject("keys");
        int n = keys.getInt("n");
        int k = keys.getInt("k");

        // List to store points
        List<Point> points = new ArrayList<>();

        // Iterate through the JSON to decode values
        for (int i = 1; i <= n; i++) {
            JSONObject pointData = jsonInput.getJSONObject(String.valueOf(i));
            int x = i; // Using the keys as x values
            int y = decodeValue(pointData.getString("base"), pointData.getString("value"));
            points.add(new Point(x, y));
        }

        // Check if we have enough points to solve for the polynomial
        if (points.size() < k) {
            System.out.println("Insufficient points to solve the polynomial.");
            return;
        }

        // Calculate the constant term 'c' using Lagrange interpolation
        double c = calculateConstantTerm(points, k);
        System.out.println("The constant term (secret 'c') of the polynomial is: " + c);
    }

    // Method to read JSON from a file
    private static JSONObject readJsonFile(String filePath) {
        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return new JSONObject(jsonBuilder.toString());
    }

    // Method to decode the value based on the base
    private static int decodeValue(String baseStr, String valueStr) {
        int base = Integer.parseInt(baseStr);
        return Integer.parseInt(valueStr, base);
    }

    // Method to calculate the constant term 'c'
    private static double calculateConstantTerm(List<Point> points, int k) {
        double c = 0.0;

        // Lagrange interpolation formula for c
        for (int i = 0; i < k; i++) {
            double term = points.get(i).y;
            for (int j = 0; j < k; j++) {
                if (i != j) {
                    term *= (0 - points.get(j).x) / (points.get(i).x - points.get(j).x);
                }
            }
            c += term;
        }
        
        return c;
    }

    // Helper class to represent a point (x, y)
    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
