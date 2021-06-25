import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageOperation {

    public static void Operate(int key){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);//component null se dialog box will open in center...
        File file = fileChooser.getSelectedFile();//whichever file you choose would be saved as file ...


        //file inputstream reader
        try{
            FileInputStream fis = new FileInputStream(file);

            byte[]data = new byte[fis.available()];
            fis.read(data);
            int i=0;
            for (byte b:data) {
                System.out.println(b);
                data[i] = (byte) (b^key);//main logic :
                                           // Only we know the key therefore we can encrypt the image once and then decrypt it using the same key only....
                                           // we encrypted the file using XOR operator....
                i++;
            }

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null,"DONE");

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        JFrame f = new JFrame();
        f.setTitle("Image Operation");
        f.setSize(400,400);
        f.setLocationRelativeTo(null);  //this will bring picture to center..
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //this will easily close the window.


        Font font = new Font("Roboto",Font.BOLD,25);
        //creating button
        JButton button = new JButton();
        button.setText("Open Image");
        button.setFont(font);


        //creating text field
        JTextField textField = new JTextField(10);
        textField.setFont(font);

        button.addActionListener(e->{
            String text = textField.getText();
            int temp = Integer.parseInt(text);
            Operate(temp);

        });//we have to pass actionListener object but actionListener is an interface and we cannot create
        //object of an interface. therefore we will create the object of the child class of interface
        //to do that we have to create a class and then implement ActionListener

        //instead of doing all that we have used lamda function here.
        //WE CAN IMPLEMENT FUNCTIONAL INTERFACE USING LAMDA FUNCTION
        //IT WOULD CREATE AN ANONYMOUS CLASS AND HELP IMPLEMENTING THIS INTERFACE..

        f.setLayout(new FlowLayout());

        f.add(button);
        f.add(textField);
        f.setVisible(true);




    }
}
