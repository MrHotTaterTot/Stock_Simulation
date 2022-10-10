import javax.swing.*;
import java.awt.*;

public class NewUser extends NewWindow{
    // Window where the user can type in their name when using the program for the first time
    // inherits the methods of it's super class NewWindow
    public NewUser(Main main, String name){
        super(main,name);
    }
    public void setFrame() {
        frame = new JFrame("New User");
        frame.setLayout(null);
        frame.setSize(400,225);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
    }

    @Override
    public void setInterface() {
        JButton submit = new JButton("submit");
        TextField name = new TextField("Enter your name");
        submit.setBounds(50,50,125,45);
        name.setBounds(200,50,125,45);
        name.addMouseListener(new TextFieldMouseListener(name));
        submit.addActionListener(new SubmitNewNameActionListener(this,main,name));
        frame.add(submit);
        frame.add(name);
    }



}
