import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class LoginScreen extends JFrame {
    private JTextField nameField;
    private JButton loginButton;

    public LoginScreen() {
        setTitle("Waiter Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1));

        JLabel welcomeLabel = new JLabel("Enter your name to login:");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        nameField = new JTextField();
        loginButton = new JButton("Login");

        panel.add(welcomeLabel);
        panel.add(nameField);
        panel.add(loginButton);
        add(panel);

        loginButton.addActionListener(e -> {
            WaitStaffManager.loadWaitstaff(); // reload from file
            String name = nameField.getText().trim();

            if (WaitStaffManager.isWaiterValid(name)) {
                dispose();
                List<Table> tables = WaitStaffManager.getAssignedTables(name); // ✅ List<Table>
                new FloorStatusScreen(name, tables); // ✅ compatible constructor
            } else {
                JOptionPane.showMessageDialog(this, "Invalid waiter name.");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}




