import java.awt.*;
import javax.swing.*;
public class main
{
    public static void main(String args[])
    {
        mainframe f1=new mainframe();
        f1.setVisible(true);

        {
            f1.setLocation(300,80);
            f1.setSize(750,600);

        }


    }
    public static class mainframe extends JFrame implements Runnable
    {
        Thread t1;
        mainframe()
        {
            super("WIFI Billing System");
            setLayout(new FlowLayout());
            ImageIcon c1=new ImageIcon(ClassLoader.getSystemResource("images/splash page.jpeg"));
            Image i1=c1.getImage().getScaledInstance(720,550,Image.SCALE_DEFAULT);
            ImageIcon i2=new ImageIcon(i1);

            JLabel l1=new JLabel(i2);
            add(l1);
            t1=new Thread(this);
            t1.start();
        }
        public void run()
        {
            try
            {
                Thread.sleep(3500);
                this.setVisible(false);
                new login().setVisible(true);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }

}
