import java.io.*;
import java.util.*;

public class WaitStaffManager {
    private static final String FILE_PATH = "waitstaff.txt";
    private static final int TOTAL_TABLES = 30;

    // Map: waiter name → set of assigned table numbers
    private static Map<String, Set<Integer>> waiterAssignments = new HashMap<>();

    public static void loadWaitstaff() {
        waiterAssignments.clear();
        System.out.println("Looking for file in: " + System.getProperty("user.dir"));

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String name = parts[0].trim().toLowerCase();
                    String[] tableNumbers = parts[1].split(",");
                    Set<Integer> tableSet = new HashSet<>();
                    for (String num : tableNumbers) {
                        int tableNum = Integer.parseInt(num.trim());
                        tableSet.add(tableNum);
                    }
                    waiterAssignments.put(name, tableSet);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading waitstaff file: " + e.getMessage());
        }
    }

    public static boolean isWaiterValid(String name) {
        return waiterAssignments.containsKey(name.toLowerCase());
    }

    public static List<Table> getAssignedTables(String name) {
        String lowerName = name.toLowerCase();
        Set<Integer> assignedSet = waiterAssignments.getOrDefault(lowerName, new HashSet<>());

        List<Table> allTables = new ArrayList<>();
        for (int i = 1; i <= TOTAL_TABLES; i++) {
            // Only assign this table to the current waiter if it's in their list
            String assignedWaiter = assignedSet.contains(i) ? lowerName : "";
            allTables.add(new Table(i, assignedWaiter));
        }
        return allTables;
    }
}

