import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;
import java.sql.*;

public class BillFormPage extends JFrame {
    private String customerId;
    private String month;
    private JLabel nameLabel;
    private JLabel customerIdLabel;
    private JLabel monthLabel;
    private JLabel amountLabel;
    private JButton printButton;

    public BillFormPage(String customerId, String month) {
        this.customerId = customerId;
        this.month = month;

        setTitle("Bill Form");
        setSize(400, 400);
        setLocation(300, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameLabel = new JLabel();
        customerIdLabel = new JLabel();
        monthLabel = new JLabel();
        amountLabel = new JLabel();

        loadBillDetails();

        panel.add(new JLabel("Customer ID:"));
        panel.add(customerIdLabel);
        panel.add(new JLabel("Name:"));
        panel.add(nameLabel);
        panel.add(new JLabel("Month:"));
        panel.add(monthLabel);
        panel.add(new JLabel("Amount:"));
        panel.add(amountLabel);

        printButton = new JButton("Print");
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printBill();
            }
        });

        panel.add(new JLabel());
        panel.add(printButton);

        add(panel);
    }

    private void loadBillDetails() {
        try {
            conn c1 = new conn();
            String query = "SELECT * FROM bill WHERE customer_id = '" + customerId + "' AND month = '" + month + "'";
            ResultSet rs = c1.s.executeQuery(query);
            if (rs.next()) {
                customerIdLabel.setText(rs.getString("customer_id"));
                nameLabel.setText(rs.getString("name"));
                monthLabel.setText(rs.getString("month"));
                amountLabel.setText(rs.getString("amount"));
            } else {
                JOptionPane.showMessageDialog(null, "Bill details not found.", "Error", JOptionPane.ERROR_MESSAGE);
                dispose(); // Close the window if the bill details are not found
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printBill() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Bill");

        Printable printable = new Printable() {
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return NO_SUCH_PAGE;
                }

                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                // Create a bill template
                StringBuilder billText = new StringBuilder();
                billText.append("========== BILL ==========\n");
                billText.append("Customer ID: ").append(customerIdLabel.getText()).append("\n");
                billText.append("Name: ").append(nameLabel.getText()).append("\n");
                billText.append("Month: ").append(monthLabel.getText()).append("\n");
                billText.append("Amount: ").append(amountLabel.getText()).append("\n");
                billText.append("==========================");

                // Print the bill
                Font font = new Font("Courier New", Font.PLAIN, 12);
                g2d.setFont(font);
                g2d.drawString(billText.toString(), 10, 10);

                return PAGE_EXISTS;
            }
        };

        job.setPrintable(printable);

        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    String customerId = "12345";
                    String month = "June";
                    BillFormPage frame = new BillFormPage(customerId, month);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
