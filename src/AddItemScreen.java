import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AddItemScreen extends JFrame {
    private String waiterName;
    private Table table; // Refactored from int tableNum
    private List<Table> tables;

    private JComboBox<String> categoryBox;
    private JComboBox<String> itemBox;

    public AddItemScreen(String waiterName, Table table, List<Table> tables) {
        this.waiterName = waiterName;
        this.table = table;
        this.tables = tables;

        setTitle("Add Item - Table " + table.getTableNumber());
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        JLabel categoryLabel = new JLabel("Choose Category:", SwingConstants.CENTER);
        categoryBox = new JComboBox<>(MenuManager.getCategories().toArray(new String[0]));

        JLabel itemLabel = new JLabel("Choose Item:", SwingConstants.CENTER);
        itemBox = new JComboBox<>();

        JButton confirmButton = new JButton("Add to Order");
        JButton backButton = new JButton("Back");

        add(categoryLabel);
        add(categoryBox);
        add(itemLabel);
        add(itemBox);
        add(confirmButton);
        add(backButton);

        updateItems(); // fill initial items from first category

        categoryBox.addActionListener(e -> updateItems());

        confirmButton.addActionListener(e -> {
            String selectedItem = (String) itemBox.getSelectedItem();
            if (selectedItem != null && !selectedItem.isEmpty()) {
                System.out.println("[QUEUE] Table " + table.getTableNumber() + " - " + selectedItem);
                JOptionPane.showMessageDialog(this, selectedItem + " added to Table " + table.getTableNumber() + "'s order.");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a valid item.");
            }
        });

        backButton.addActionListener(e -> {
            dispose();
            new TableOptionsScreen(waiterName, table, tables);
        });

        setVisible(true);
    }

    private void updateItems() {
        String selectedCategory = (String) categoryBox.getSelectedItem();
        itemBox.removeAllItems();

        List<String> items = MenuManager.getItemsForCategory(selectedCategory);
        for (String item : items) {
            itemBox.addItem(item);
        }
    }
}
