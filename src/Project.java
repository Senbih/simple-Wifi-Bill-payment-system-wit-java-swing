import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Project extends JFrame implements ActionListener{
    Project(){
        super("WIFI Billing System");

        setSize(1500,800);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //back image
        ImageIcon ic =  new ImageIcon(ClassLoader.getSystemResource("images/front page.jpeg"));
        Image i3 = ic.getImage().getScaledInstance(1420, 720,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel l1 = new JLabel(icc3);
        add(l1);

        //menu
        JMenuBar mb  = new JMenuBar();
        JMenu master = new JMenu("CUSTOMER");
        JMenuItem m1 = new JMenuItem("ADD new Customer");
        JMenuItem m2 = new JMenuItem("Customer Details");
        master.setForeground(Color.ORANGE);


        //add cust
        m1.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("images/icon1.jpg"));
        Image image1 = icon1.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        m1.setIcon(new ImageIcon(image1));
        m1.setBackground(Color.WHITE);

        //cust detail
        m2.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("images/icon2.png"));
        Image image2 = icon2.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        m2.setIcon(new ImageIcon(image2));
        m2.setBackground(Color.WHITE);



        m1.addActionListener(this);
        m2.addActionListener(this);





        //bill
        JMenu user = new JMenu("Bill");
        JMenuItem u1 = new JMenuItem("Pay Bill");
        JMenuItem u2 = new JMenuItem("Bill Details");
        user.setForeground(Color.black);

        //pay bill
        u1.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("images/icon4.png"));
        Image image4 = icon4.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        u1.setIcon(new ImageIcon(image4));
        u1.setBackground(Color.WHITE);

        //bill detail
        u2.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("images/icon5.png"));
        Image image5 = icon5.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        u2.setIcon(new ImageIcon(image5));
        u2.setBackground(Color.WHITE);


        u1.addActionListener(this);
        u2.addActionListener(this);




        //Report
        JMenu report = new JMenu("Report");
        JMenuItem r1 = new JMenuItem("Generate Bill");
        report.setForeground(Color.BLUE);

        //BillGenertor
        r1.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("images/icon7.png"));
        Image image7 = icon7.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        r1.setIcon(new ImageIcon(image7));
        r1.setBackground(Color.WHITE);

        r1.addActionListener(this);

        //admin
        JMenu admin = new JMenu("Admin");
        JMenuItem a1 = new JMenuItem("Add Admin");
        JMenuItem a2 = new JMenuItem("All Admins");
        admin.setForeground(Color.red);

        //Add admin
        a1.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("images/icon7.png"));
        Image image6 = icon6.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        a1.setIcon(new ImageIcon(image6));
        a1.setBackground(Color.WHITE);

        //All admins
        a2.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("images/icon1.jpg"));
        Image image8 = icon8.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        a2.setIcon(new ImageIcon(image8));
        a2.setBackground(Color.WHITE);


        a1.addActionListener(this);
        a2.addActionListener(this);


        //Exit
        JMenu exit = new JMenu("Exit");
        JMenuItem ex = new JMenuItem("Exit");
        exit.setForeground(Color.BLUE);


        ex.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("images/icon11.png"));
        Image image11 = icon11.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        ex.setIcon(new ImageIcon(image11));

        ex.setBackground(Color.WHITE);

        ex.addActionListener(this);





        master.add(m1);
        master.add(m2);


        user.add(u1);
        user.add(u2);

        report.add(r1);

        admin.add(a1);
        admin.add(a2);


        exit.add(ex);

        mb.add(master);
        mb.add(user);
        mb.add(report);
        mb.add(admin);
        mb.add(exit);

        setJMenuBar(mb);

        setFont(new Font("Senserif",Font.BOLD,16));
        setLayout(new FlowLayout());
        setVisible(false);
    }
    public void actionPerformed(ActionEvent ae){
        String msg = ae.getActionCommand();
        if(msg.equals("Customer Details")){
            new customer_details().setVisible(true);

        }else if(msg.equals("ADD new Customer")){
            new new_customer().setVisible(true);

        }else if(msg.equals("Bill Details")){
            new bill_details().setVisible(true);

        }else if(msg.equals("Pay Bill")){
            new pay_bill().setVisible(true);

        }else if(msg.equals("Generate Bill")){
             new GenerateBill().setVisible(true);

        } else if(msg.equals("Add Admin")){
            new add_admin().setVisible(true);

        }else if(msg.equals("All Admins")){
            new admin_detail().setVisible(true);

        }else if(msg.equals("Exit")){
            System.exit(0);
        }


    }


    public static void main(String[] args){
        new Project().setVisible(true);
    }

}