import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class GenerateBill extends JFrame implements ActionListener {

    JTable t1;
    JButton b1;
    JButton refreshButton;
    String x[] = {"Customer ID", "Name", "Month", "Amount"};
    String y[][] = new String[20][4];
    int i = 0, j = 0;
    JTextField searchField; // Add a search field

    GenerateBill() {
        super("Generate Bill");
        setSize(800, 400);
        setLocation(200, 200);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            conn c1 = new conn();
            String s1 = "SELECT * FROM bill";
            ResultSet rs = c1.s.executeQuery(s1);

            while (rs.next()) {
                y[i][j++] = rs.getString("customer_id");
                y[i][j++] = rs.getString("name");
                y[i][j++] = rs.getString("month");
                y[i][j++] = rs.getString("amount");
                i++;
                j = 0;
            }
            t1 = new JTable(y, x);

        } catch (Exception e) {
            e.printStackTrace();
        }

        searchField = new JTextField(); // Create a new text field for search
        searchField.addActionListener(this);
        add(searchField, BorderLayout.NORTH);

        b1 = new JButton("Search");
        refreshButton = new JButton("Refresh"); // Create the refresh button

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(b1);
        buttonPanel.add(refreshButton);

        b1.addActionListener(this);
        refreshButton.addActionListener(this);

        add(buttonPanel, BorderLayout.SOUTH);

        JScrollPane sp = new JScrollPane(t1);
        add(sp, BorderLayout.CENTER);

        // Add a mouse listener to the table
        t1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                int row = t1.getSelectedRow();
                if (row != -1) {
                    String customerId = (String) t1.getValueAt(row, 0);
                    String month = (String) t1.getValueAt(row, 2);
                    openBillFormPage(customerId, month);
                }
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            String searchTerm = searchField.getText();
            search(searchTerm);
        } else if (ae.getSource() == refreshButton) {
            refreshTable();
        }
    }

    public void search(String searchTerm) {
        for (int row = 0; row < y.length; row++) {
            for (int col = 0; col < y[row].length; col++) {
                y[row][col] = null;
            }
        }
        // Perform the search in the database using the searchTerm
        try {
            conn c1 = new conn();
            String s1 = "SELECT * FROM bill WHERE customer_id LIKE '%" + searchTerm + "%'";
            ResultSet rs = c1.s.executeQuery(s1);
            int row = 0;
            while (rs.next()) {
                y[row][0] = rs.getString("customer_id");
                y[row][1] = rs.getString("name");
                y[row][2] = rs.getString("month");
                y[row][3] = rs.getString("amount");
                row++;
            }
            t1.repaint(); // Repaint the table with search results

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshTable() {
        // Clear the table
        for (int row = 0; row < y.length; row++) {
            for (int col = 0; col < y[row].length; col++) {
                y[row][col] = null;
            }
        }

        // Retrieve the original data from the database
        try {
            conn c1 = new conn();
            String s1 = "SELECT * FROM bill";
            ResultSet rs = c1.s.executeQuery(s1);
            int row = 0;
            while (rs.next()) {
                y[row][0] = rs.getString("customer_id");
                y[row][1] = rs.getString("name");
                y[row][2] = rs.getString("month");
                y[row][3] = rs.getString("amount");
                row++;
            }
            t1.repaint(); // Repaint the table with the original data

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openBillFormPage(String customerId, String month) {
        EventQueue.invokeLater(() -> {
            try {
                BillFormPage frame = new BillFormPage(customerId, month);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        new GenerateBill().setVisible(true);
    }
}