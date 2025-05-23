import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TableOptionsScreen extends JFrame {
    private String waiterName;
    private Table table;
    private List<Table> tables;

    public TableOptionsScreen(String waiterName, Table table, List<Table> tables) {
        this.waiterName = waiterName;
        this.table = table;
        this.tables = tables;

        setTitle("Table " + table.getTableNumber() + " Options - Waiter: " + waiterName);
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1, 10, 10));

        JLabel titleLabel = new JLabel("Table " + table.getTableNumber() + " - Options", SwingConstants.CENTER);
        JButton addItemButton = new JButton("Add Item");
        JButton markOccupiedButton = new JButton("Mark as Occupied");
        JButton markDirtyButton = new JButton("Mark as Dirty");
        JButton markCleanButton = new JButton("Mark as Clean");
        JButton goBackButton = new JButton("Back to Floor");

        add(titleLabel);
        add(addItemButton);
        add(markOccupiedButton);
        add(markDirtyButton);
        add(markCleanButton);
        add(goBackButton);

        // ➕ Add Item
        addItemButton.addActionListener(e -> {
            dispose();
            new AddItemScreen(waiterName, table, tables); // ✅ Pass Table object
        });

        // 🟨 Mark as Occupied
        markOccupiedButton.addActionListener(e -> {
            table.setStatus("occupied");
            JOptionPane.showMessageDialog(this, "Table marked as occupied.");
            dispose();
            new FloorStatusScreen(waiterName, tables);
        });

        // 🟥 Mark as Dirty
        markDirtyButton.addActionListener(e -> {
            table.setStatus("dirty");
            JOptionPane.showMessageDialog(this, "Table marked as dirty.");
            dispose();
            new FloorStatusScreen(waiterName, tables);
        });

        // 🟩 Mark as Clean
        markCleanButton.addActionListener(e -> {
            table.setStatus("open");
            JOptionPane.showMessageDialog(this, "Table marked as open.");
            dispose();
            new FloorStatusScreen(waiterName, tables);
        });

        // 🔙 Go Back
        goBackButton.addActionListener(e -> {
            dispose();
            new FloorStatusScreen(waiterName, tables);
        });

        setVisible(true);
    }
}



