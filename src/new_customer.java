import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class new_customer extends JFrame implements ActionListener {
    JLabel l1, l2,  l4, l5,  l7, l8;
    JTextField t1, t4, t5, t7;
    JComboBox<Integer> t2;
    JButton b1, b2;

    new_customer() {
        super("Add Customer");
        setLocation(350, 200);
        setSize(650, 600);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }


        JPanel p = new JPanel();
        p.setLayout(new GridLayout(6, 2, 10, 10));

        p.setBackground(Color.WHITE);

        l4 = new JLabel("ID");
        t4 = new JTextField();
        p.add(l4);
        p.add(t4);
        l1 = new JLabel("Name");
        t1 = new JTextField();
        p.add(l1);
        p.add(t1);
        l2 = new JLabel("Mbp/sec");
        t2 = new JComboBox<>(new Integer[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 50});
        p.add(l2);
        p.add(t2);
        l5 = new JLabel("City");
        t5 = new JTextField();
        p.add(l5);
        p.add(t5);
        l7 = new JLabel("Phone Number");
        t7 = new JTextField();
        p.add(l7);
        p.add(t7);

        b1 = new JButton("Submit");
        b2 = new JButton("Cancel");

        p.add(b1);
        p.add(b2);
        setLayout(new BorderLayout());

        add(p, "Center");



        getContentPane().setBackground(Color.WHITE);

        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        String g = t4.getText();
        String a = t1.getText();
        int c = (int) t2.getSelectedItem();
        String f = t5.getText();
        String h = t7.getText();

        String q1 = "insert into customer values('" + g + "','" + a + "','" + c + "','" + f + "','" + h + "')";

        try {
            conn c1 = new conn();
            c1.s.executeUpdate(q1);
            JOptionPane.showMessageDialog(null, "Customer Created");
            this.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new new_customer().setVisible(true);
    }
}
