import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class add_admin extends JFrame implements ActionListener {
    JLabel l1,  l4;
    JTextField t4;
    JPasswordField t1;

    JButton b1, b2;

    add_admin() {
        super("Add Admin");
        setLocation(350, 200);
        setSize(650, 600);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }


        JPanel p = new JPanel();
        p.setLayout(new GridLayout(9, 2, 10, 10));

        p.setBackground(Color.WHITE);

        l4 = new JLabel("Username");
        t4 = new JTextField();
        p.add(l4);
        p.add(t4);
        l1 = new JLabel("password");
        t1 = new JPasswordField();
        p.add(l1);
        p.add(t1);

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

        String q1 = "insert into login values('" + g + "','" + a + "')";

        try {
            conn c1 = new conn();
            c1.s.executeUpdate(q1);
            JOptionPane.showMessageDialog(null, "admin added");
            this.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new add_admin().setVisible(true);
    }
}
