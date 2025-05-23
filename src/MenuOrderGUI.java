import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

public class MenuOrderGUI extends JFrame {
    private JComboBox<MenuCategory> categoryCombo;
    private JTextField tableField, quantityField;
    private JTextArea orderArea;
    private JButton addToOrderButton, finishOrderButton;

    private Map<MenuCategory, List<MenuItem>> menuMap = new HashMap<>();
    private Order currentOrder;

    public MenuOrderGUI() {
        setTitle("Waiter Order System");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeMenu();

        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        tableField = new JTextField();
        inputPanel.add(new JLabel("Table Number:"));
        inputPanel.add(tableField);

        categoryCombo = new JComboBox<>(MenuCategory.values());
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryCombo);

        quantityField = new JTextField("1");
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);

        addToOrderButton = new JButton("Add to Order");
        finishOrderButton = new JButton("Finish Order");

        inputPanel.add(addToOrderButton);
        inputPanel.add(new JLabel()); // spacing

        orderArea = new JTextArea();
        orderArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(finishOrderButton, BorderLayout.SOUTH);

        addToOrderButton.addActionListener(e -> addItemToOrder());
        finishOrderButton.addActionListener(e -> showFinalOrder());
    }

    private void initializeMenu() {
        for (MenuCategory category : MenuCategory.values()) {
            menuMap.put(category, new ArrayList<>());
        }

        menuMap.get(MenuCategory.PIZZA_CALZONE).addAll(List.of(
                new MenuItem("Pepperoni Pizza", MenuCategory.PIZZA_CALZONE, 12.99),
                new MenuItem("Cheese Pizza", MenuCategory.PIZZA_CALZONE, 11.49),
                new MenuItem("Meat Lovers Pizza", MenuCategory.PIZZA_CALZONE, 14.99),
                new MenuItem("Veggie Calzone", MenuCategory.PIZZA_CALZONE, 13.49)
        ));

        menuMap.get(MenuCategory.PASTA).addAll(List.of(
                new MenuItem("Spaghetti Marinara", MenuCategory.PASTA, 10.99),
                new MenuItem("Fettuccine Alfredo", MenuCategory.PASTA, 11.99),
                new MenuItem("Baked Ziti", MenuCategory.PASTA, 12.49),
                new MenuItem("Pesto Penne", MenuCategory.PASTA, 11.49)
        ));

        menuMap.get(MenuCategory.SIDES).addAll(List.of(
                new MenuItem("Garlic Bread", MenuCategory.SIDES, 3.49),
                new MenuItem("Cheesy Breadsticks", MenuCategory.SIDES, 4.49),
                new MenuItem("Side Salad", MenuCategory.SIDES, 4.99),
                new MenuItem("Mozzarella Sticks", MenuCategory.SIDES, 5.99)
        ));

        menuMap.get(MenuCategory.DESSERTS).addAll(List.of(
                new MenuItem("Chocolate Lava Cake", MenuCategory.DESSERTS, 6.49),
                new MenuItem("Tiramisu", MenuCategory.DESSERTS, 5.99),
                new MenuItem("Cannoli", MenuCategory.DESSERTS, 4.99),
                new MenuItem("Gelato", MenuCategory.DESSERTS, 4.49)
        ));

        menuMap.get(MenuCategory.DRINKS).addAll(List.of(
                new MenuItem("Soda", MenuCategory.DRINKS, 1.99),
                new MenuItem("Bottled Water", MenuCategory.DRINKS, 1.49),
                new MenuItem("Iced Tea", MenuCategory.DRINKS, 2.49),
                new MenuItem("Sparkling Water", MenuCategory.DRINKS, 2.99)
        ));
    }

    private void addItemToOrder() {
        if (currentOrder == null) {
            try {
                int tableNum = Integer.parseInt(tableField.getText());
                currentOrder = new Order(tableNum);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Enter a valid table number.");
                return;
            }
        }

        MenuCategory category = (MenuCategory) categoryCombo.getSelectedItem();
        List<MenuItem> items = menuMap.get(category);

        String[] itemNames = items.stream().map(MenuItem::getName).toArray(String[]::new);
        String selected = (String) JOptionPane.showInputDialog(this, "Select item:", "Menu Items",
                JOptionPane.PLAIN_MESSAGE, null, itemNames, itemNames[0]);

        if (selected != null) {
            MenuItem selectedItem = items.stream()
                    .filter(i -> i.getName().equals(selected))
                    .findFirst()
                    .orElse(null);

            try {
                int qty = Integer.parseInt(quantityField.getText());
                currentOrder.addItem(selectedItem, qty);
                orderArea.append("Added: " + selectedItem.getName() + " x" + qty + "\n");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid quantity.");
            }
        }
    }

    private void showFinalOrder() {
        if (currentOrder == null || currentOrder.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items in order.");
            return;
        }

        StringBuilder summary = new StringBuilder();
        summary.append("\n\nFinal Order for Table ").append(currentOrder.getTableNumber()).append(":\n");
        for (OrderItem item : currentOrder.getItems()) {
            summary.append("- ").append(item.getItem().getName()).append(" x").append(item.getQuantity()).append("\n");
        }

        orderArea.append(summary.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuOrderGUI().setVisible(true));
    }

    enum MenuCategory {
        PIZZA_CALZONE, PASTA, SIDES, DESSERTS, DRINKS
    }

    static class MenuItem {
        private String name;
        private MenuCategory category;
        private double cost;

        public MenuItem(String name, MenuCategory category, double cost) {
            this.name = name;
            this.category = category;
            this.cost = cost;
        }

        public String getName() {
            return name;
        }

        public MenuCategory getCategory() {
            return category;
        }

        public double getCost() {
            return cost;
        }
    }

    static class OrderItem {
        private MenuItem item;
        private int quantity;

        public OrderItem(MenuItem item, int quantity) {
            this.item = item;
            this.quantity = quantity;
        }

        public MenuItem getItem() {
            return item;
        }

        public int getQuantity() {
            return quantity;
        }
    }

    static class Order {
        private int tableNumber;
        private List<OrderItem> items;

        public Order(int tableNumber) {
            this.tableNumber = tableNumber;
            this.items = new ArrayList<>();
        }

        public void addItem(MenuItem item, int quantity) {
            items.add(new OrderItem(item, quantity));
        }

        public List<OrderItem> getItems() {
            return items;
        }

        public int getTableNumber() {
            return tableNumber;
        }
    }
}