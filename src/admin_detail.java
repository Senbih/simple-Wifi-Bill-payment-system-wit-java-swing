import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class admin_detail extends JFrame implements ActionListener {


    JTable t1;
    JButton b1;
    JButton refreshButton;
    String x[] = {"username","password"};
    String y[][] = new String[20][2];
    int i=0, j=0;
    JTextField searchField;

    admin_detail(){
        super("Admin Details");
        setSize(1200,650);
        setLocation(70,50);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            conn c1  = new conn();
            String s1 = "select * from login";
            ResultSet rs  = c1.s.executeQuery(s1);
            while(rs.next()){
                y[i][j++]=rs.getString("username");
                y[i][j++]=rs.getString("password");

                i++;
                j=0;
            }
            t1 = new JTable(y,x);

        }catch(Exception e){
            e.printStackTrace();
        }

        searchField = new JTextField();
        searchField.addActionListener(this);
        add(searchField, BorderLayout.NORTH);

        b1 = new JButton("Search");
        refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(b1);
        buttonPanel.add(refreshButton);

        b1.addActionListener(this);
        refreshButton.addActionListener(this);

        add(buttonPanel, BorderLayout.SOUTH);

        JScrollPane sp = new JScrollPane(t1);
        add(sp, BorderLayout.CENTER);


        t1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                int row = t1.getSelectedRow();
                if (row != -1) {
                    String username = (String) t1.getValueAt(row, 0);
                    openUpdateAdmin(username);
                }
            }
        });
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == b1) {
            String searchTerm = searchField.getText();
            search(searchTerm);
        }
        else if (ae.getSource() == refreshButton) {
            refreshTable();
        }
    }

    public void search(String searchTerm) {
        for (int row = 0; row < y.length; row++) {
            for (int col = 0; col < y[row].length; col++) {
                y[row][col] = null;
            }
        }

        try {
            conn c1  = new conn();
            String s1 = "select * from login where username like '%" + searchTerm + "%'";
            ResultSet rs  = c1.s.executeQuery(s1);
            int row = 0;
            while(rs.next()){
                y[row][0]=rs.getString("username");
                y[row][1]=rs.getString("password");

                row++;
            }
            t1.repaint();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void refreshTable() {

        for (int row = 0; row < y.length; row++) {
            for (int col = 0; col < y[row].length; col++) {
                y[row][col] = null;
            }
        }


        try {
            conn c1  = new conn();
            String s1 = "select * from login";
            ResultSet rs  = c1.s.executeQuery(s1);
            int row = 0;
            while(rs.next()){
                y[row][0]=rs.getString("username");
                y[row][1]=rs.getString("password");

                row++;
            }
            t1.repaint();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void openUpdateAdmin(String username) {
        EventQueue.invokeLater(() -> {
            try {
                UpdateAdminDetails frame = new UpdateAdminDetails(username);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args){
        new admin_detail().setVisible(true);
    }
}
