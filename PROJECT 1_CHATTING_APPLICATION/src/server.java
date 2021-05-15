import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class server extends JFrame implements ActionListener {
    JPanel p1;//we declare this globally or else it would not have any scope outside the class
    //panel works same as div tag in html...
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    static ServerSocket skt;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;

    server(){

        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        add(p1);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);//we cannot directly get a image on frame therefore we need to create a label.
        l1.setBounds(5,17,30,30);
        p1.add(l1);

        //for exiting when we click back arrow.
        l1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
//code above is for back arrow

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
        Image i5 = i4.getImage().getScaledInstance(35,40,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);//we cannot directly get a image on frame therefore we need to create a label.
        l2.setBounds(40,15,30,30);
        p1.add(l2);
//code above is for dinosaur user image

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l5 = new JLabel(i9);//we cannot directly get a image on frame therefore we need to create a label.
        l5.setBounds(315,15,30,30);
        p1.add(l5);

        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel l6 = new JLabel(i12);//we cannot directly get a image on frame therefore we need to create a label.
        l6.setBounds(365,15,30,30);
        p1.add(l6);

        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel l7 = new JLabel(i15);//we cannot directly get a image on frame therefore we need to create a label.
        l7.setBounds(410,15,15,30);
        p1.add(l7);


        JLabel l3 = new JLabel("DINOSIR");
        l3.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        l3.setForeground(Color.white);//changes color of the font...
        l3.setBounds(80,10,100,20);
        p1.add(l3);
//above code is for display name

        JLabel l4 = new JLabel("Active Now");
        l4.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        l4.setForeground(Color.white);//changes color of the font...
        l4.setBounds(80,28,100,20);
        p1.add(l4);
//above code is for active status


//middle section
        a1=new JTextArea();
        a1.setBounds(5,75,440,575);
        a1.setFont(new Font("SANS_SERIF",Font.PLAIN,16));
        a1.setEditable(false);//to not let user edin in the texxt area
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        add(a1);



        //code for text field at the bottom...
        t1=new JTextField();
        t1.setBounds(5,660,350,35);
        t1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        add(t1);

        //code for button at the bottom lower-right
        b1 = new JButton("Send");
        b1.setBounds(360,660,75,35);
        b1.setBackground(new Color(7,94,84));
        b1.setForeground(Color.white);
        b1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        b1.addActionListener(this);//we want that a action is performed when we click this button what action will be performed will
        //be shown under actionPerformed method which is given below..
        add(b1);



        //for panel
        setLayout(null);
        setSize(450,700);
        setUndecorated(true);
        setLocation(300,90);
        setVisible(true);//this will come always at last...
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       try { //taking text from senders textfield and taking it to common text area...
           String out = t1.getText();
//now when we are showing the text on text area we want previous messages to be visible too therefore we append new message to old messages
           a1.setText(a1.getText() + "\n\t\t\t\t" + out);
           dout.writeUTF(out);
           t1.setText("");//because we want the text field to be empty after sending the message..
       }catch (Exception e2){

       }
    }

    public static void main(String[] args) {
        new server().setVisible(true);

        String msginput = "";
        try{
            skt=new ServerSocket(6001);//socket object
            s = skt.accept();//server object


            //for tracking messages...
            din = new DataInputStream(s.getInputStream());//wo data jo aayega
            dout = new DataOutputStream(s.getOutputStream());//wo data jo apan bhejenge
            msginput = din.readUTF();//to read data
            a1.setText(a1.getText()+"\n"+msginput);

            skt.close();
            s.close();

        }catch (Exception e)
        {

        }

    }


}
