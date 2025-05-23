import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FloorStatusScreen extends JFrame {
    private String waiterName;
    private List<Table> tables;

    public FloorStatusScreen(String waiterName, List<Table> tables) {
        this.waiterName = waiterName;
        this.tables = tables;

        setTitle("Floor Status - Welcome " + waiterName);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 6, 10, 10));

        for (Table table : tables) {
            JButton tableButton = new JButton("Table " + table.getTableNumber());
            tableButton.setToolTipText("Seats: 4\nAssigned to: " + table.getAssignedServer());

            tableButton.setBackground(getColorForStatus(table.getStatus()));

            if (!table.getAssignedServer().equalsIgnoreCase(waiterName)) {
                tableButton.setEnabled(false);
            }

            tableButton.addActionListener(e -> {
                dispose();
                new TableOptionsScreen(waiterName, table, tables);
            });

            add(tableButton);
        }

        setVisible(true);
    }

    private Color getColorForStatus(String status) {
        switch (status) {
            case "occupied": return Color.YELLOW;
            case "dirty": return Color.RED;
            default: return Color.GREEN;
        }
    }
}



