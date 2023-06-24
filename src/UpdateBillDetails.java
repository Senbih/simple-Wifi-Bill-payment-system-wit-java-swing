import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class UpdateBillDetails extends JFrame implements ActionListener {
    JLabel customerIdLabel, monthLabel;
    JTextField customerIdField, monthField;
    JButton updateButton;
    JButton deleteButton; // Add a delete button

    UpdateBillDetails(String customerId, String month) {
        super("Update Bill Details");
        setLocation(350, 200);
        setSize(350, 200);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBackground(Color.WHITE);

        customerIdLabel = new JLabel("Customer ID:");
        customerIdField = new JTextField(customerId);
        monthLabel = new JLabel("Month:");
        monthField = new JTextField(month);

        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete"); // Create the delete button

        panel.add(customerIdLabel);
        panel.add(customerIdField);
        panel.add(monthLabel);
        panel.add(monthField);
        panel.add(updateButton);
        panel.add(deleteButton); // Add the delete button to the panel

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        updateButton.addActionListener(this);
        deleteButton.addActionListener(this); // Register the delete button for ActionListener events
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateButton) {
            // Update button action
            String customerId = customerIdField.getText();
            String month = monthField.getText();

            try {
                conn c1 = new conn();
                String s1 = "SELECT * FROM customer WHERE id = '" + customerId + "'";
                ResultSet resultSet = c1.s.executeQuery(s1);

                if (resultSet.next()) {
                    String customerName = resultSet.getString("name");
                    int mbpSec = resultSet.getInt("Mbp/sec");
                    int amount = mbpSec * 10; // Calculate amount to pay

                    // Update the payment details in the bill table
                    String query = "UPDATE bill SET name = '" + customerName + "', month = '" + month + "', amount = " + amount + " WHERE customer_id = '" + customerId + "'";
                    c1.s.executeUpdate(query);

                    JOptionPane.showMessageDialog(null, "Bill details updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Customer not found!");
                }

                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (ae.getSource() == deleteButton) {
            // Delete button action
            String customerId = customerIdField.getText();
            String month = monthField.getText();

            try {
                conn c1 = new conn();
                String query = "DELETE FROM bill WHERE customer_id = '" + customerId + "' AND month = '" + month + "'";
                int rowsAffected = c1.s.executeUpdate(query);

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Bill record deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete bill record.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new UpdateBillDetails("customer123", "May 2023").setVisible(true);
    }
}
