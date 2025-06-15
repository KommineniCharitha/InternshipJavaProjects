package pkg1;
import java.util.*;

public class RecommenderApp {
    public static void main(String[] args) {
        // Sample user-item ratings (user -> item -> rating)
        Map<String, Map<String, Integer>> data = new HashMap<>();

        Map<String, Integer> u1 = new HashMap<>();
        u1.put("ItemA", 5);
        u1.put("ItemB", 3);
        data.put("User1", u1);

        Map<String, Integer> u2 = new HashMap<>();
        u2.put("ItemA", 4);
        u2.put("ItemC", 4);
        data.put("User2", u2);

        Map<String, Integer> u3 = new HashMap<>();
        u3.put("ItemA", 2);
        u3.put("ItemB", 5);
        u3.put("ItemC", 3);
        data.put("User3", u3);

        Map<String, Integer> u4 = new HashMap<>();
        u4.put("ItemB", 2);
        u4.put("ItemC", 5);
        data.put("User4", u4);

        // Recommendation logic: find top items not yet rated by User1
        Set<String> ratedItems = data.get("User1").keySet();
        Map<String, Double> itemScores = new HashMap<>();

        for (String user : data.keySet()) {
            if (!user.equals("User1")) {
                for (Map.Entry<String, Integer> entry : data.get(user).entrySet()) {
                    String item = entry.getKey();
                    int rating = entry.getValue();
                    if (!ratedItems.contains(item)) {
                        itemScores.put(item, itemScores.getOrDefault(item, 0.0) + rating);
                    }
                }
            }
        }

        // Sort and recommend
        List<Map.Entry<String, Double>> sorted = new ArrayList<>(itemScores.entrySet());
        sorted.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        System.out.println("Recommended items for User1:");
        for (Map.Entry<String, Double> entry : sorted) {
            System.out.println(entry.getKey() + " - Score: " + entry.getValue());
        }
    }
}
