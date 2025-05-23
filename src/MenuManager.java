import java.util.*;

public class MenuManager {
    private static final Map<String, List<String>> menuData = new LinkedHashMap<>();

    static {
        // 5 Categories with 4+ items each
        menuData.put("Soups/Salads", Arrays.asList("Caesar Salad", "Tomato Soup", "Greek Salad", "Cobb Salad"));
        menuData.put("Entrees", Arrays.asList("Grilled Chicken", "Steak", "Spaghetti", "Fish & Chips"));
        menuData.put("Appetizers", Arrays.asList("Mozzarella Sticks", "Spring Rolls", "Garlic Bread", "Bruschetta"));
        menuData.put("Desserts", Arrays.asList("Cheesecake", "Brownie", "Ice Cream", "Apple Pie"));
        menuData.put("Drinks", Arrays.asList("Coke", "Water", "Lemonade", "Iced Tea"));
    }

    public static Set<String> getCategories() {
        return menuData.keySet();
    }

    public static List<String> getItemsForCategory(String category) {
        return menuData.getOrDefault(category, Collections.emptyList());
    }
}
