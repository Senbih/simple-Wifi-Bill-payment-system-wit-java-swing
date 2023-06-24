import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class UpdateAdminDetails extends JFrame implements ActionListener {

    JTextField usernameField, passwordField;
    JButton updateButton;
    JButton deleteButton;
    String username;

    UpdateAdminDetails(String username) {
        super("Update Admin Details");
        setSize(400, 300);
        setLocation(400, 200);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.username = username;

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JTextField();

        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        setLayout(new GridLayout(7, 2));

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(updateButton);
        add(deleteButton);

        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);

        try {
            conn c1 = new conn();
            String query = "SELECT * FROM login WHERE username=?";
            PreparedStatement pstmt = c1.c.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usernameField.setText(rs.getString("username"));
                passwordField.setText(rs.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateButton) {
            String newUsername = usernameField.getText();
            String newPassword = passwordField.getText();

            try {
                conn c1 = new conn();
                String query = "UPDATE login SET password=? WHERE username=?";
                PreparedStatement pstmt = c1.c.prepareStatement(query);
                pstmt.setString(1, newPassword);
                pstmt.setString(2, newUsername);
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Password changed successfully!");

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating admin password. Please try again.");
            }
        } else if (ae.getSource() == deleteButton) {
            String currentUsername = usernameField.getText();

            try {
                conn c1 = new conn();
                String query = "DELETE FROM login WHERE username=?";
                PreparedStatement pstmt = c1.c.prepareStatement(query);
                pstmt.setString(1, currentUsername);
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Admin record deleted successfully!");
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete admin.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new UpdateAdminDetails("username").setVisible(true);
    }
}
