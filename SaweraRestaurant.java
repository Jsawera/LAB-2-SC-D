package lab14.sawerarestaurant;  //Ramesha Javed, Section: D, 2022f-BSE-194
import java.util.HashMap;
import java.util.Map;

public class SaweraRestaurant {
    public static void main(String[] args) {
     
        Map<String, Double> menu = new HashMap<>();
        menu.put("Biryani", 12.99);
        menu.put("Pasta", 9.99);
        menu.put("Grilled Chicken", 15.99);
        menu.put("Vegetarian Pizza", 11.99);
        menu.put("Chocolate Cake", 6.99);

        Map<Integer, Integer> tableConnections = new HashMap<>();
        tableConnections.put(1, 2);  
        tableConnections.put(2, 3);  
        tableConnections.put(3, 1);  

        System.out.println("\nTable Relationships in Sawera Restaurant:");
        printTableConnections(tableConnections);
    }

    private static void printTableConnections(Map<Integer, Integer> tableConnections) {
        for (Map.Entry<Integer, Integer> entry : tableConnections.entrySet()) {
            System.out.println("Table " + entry.getKey() + " is near Table " + entry.getValue());
        }
    }
}

