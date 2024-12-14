//ABDULLHA_Arshad

package assignment_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.border.EmptyBorder;

public class Client  implements ActionListener {
    JTextField text;
    static JPanel pp1;
    static Box vertical = Box.createVerticalBox();
        static JFrame f= new JFrame();
        static  DataOutputStream dout;


    Client() {
        f.setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        f.add(p1);

        ImageIcon il = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image p2 = il.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon p3 = new ImageIcon(p2);
        JLabel back = new JLabel(p3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        ImageIcon icon14 = new ImageIcon(ClassLoader.getSystemResource("icons/abdullah.jpeg"));
        Image image15 = icon14.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon icon16 = new ImageIcon(image15);
        JLabel profile = new JLabel(icon16);
        profile.setBounds(40, 10, 50, 50);
        p1.add(profile);

        JLabel name = new JLabel("Abdullah");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN SERIF", Font.BOLD, 18));
        p1.add(name);

        JLabel status = new JLabel("Active Now");
        status.setBounds(110, 35, 100, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN SERIF", Font.BOLD, 14));
        p1.add(status);

        pp1 = new JPanel();
        pp1.setBounds(5, 75, 440, 535);
        f.add(pp1);

        text = new JTextField();
        text.setBounds(5, 610, 310, 40);
        text.setFont(new Font("SAN SERIF", Font.PLAIN, 16));
        f.add(text);

        JButton send = new JButton("Send");
        send.setBounds(320, 610, 123, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN SERIF", Font.PLAIN, 16));
        f.add(send);

        f.setSize(450, 700);
        f.setLocation(800, 50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
        String out = text.getText();

        JPanel ppp1 = formatLabel(out);

        pp1.setLayout(new BorderLayout());
        JPanel right = new JPanel(new BorderLayout());
        right.add(ppp1, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        pp1.add(vertical, BorderLayout.PAGE_START);

        dout.writeUTF(out);

                
        text.setText("");
        f.repaint();
        f.invalidate();
        f.validate();
    }
         catch (Exception e) {
    e.printStackTrace();
}
    }
    public static JPanel formatLabel(String out) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    JLabel output = new JLabel(out);
    output.setFont(new Font("Tahoma", Font.PLAIN, 16));
    output.setBackground(new Color(37, 211, 102));
    output.setOpaque(true);
    output.setBorder(new EmptyBorder(15, 15, 15, 50));
    panel.add(output);
    
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    JLabel time = new JLabel();
    time.setText(sdf.format(cal.getTime()));
    panel.add(time);

    return panel;
}
    
    public static void main(String[] args) {
        new Client();
        
        try {
    Socket s = new Socket("127.0.0.1", 6001);
    DataInputStream din = new DataInputStream(s.getInputStream());
    DataOutputStream dout = new DataOutputStream(s.getOutputStream());
    
    while(true) {
        pp1.setLayout(new BorderLayout());
        String msg = din.readUTF();
        JPanel panel = formatLabel(msg);
        JPanel left = new JPanel(new BorderLayout());
        left.add(panel, BorderLayout.LINE_START);
        vertical.add(left);
        
        vertical.add(Box.createVerticalStrut(15));
        pp1.add(vertical, BorderLayout.PAGE_START);  
        
        f.validate();
    }
} catch (Exception e) {
    e.printStackTrace();
    System.out.println("not connection");
}
    }
}
