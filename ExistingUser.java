import javax.swing.*;
import java.awt.*;

public class ExistingUser extends NewWindow {
    //Window where the user can type in the name when they last used the program
    //inherits the methods of it's super class NewWindow

    public ExistingUser(Main main, String name){
        super(main,name);
    }
    @Override
    public void setFrame() {
        frame = new JFrame("Existing User");
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
        submit.addActionListener(new SubmitExistingNameActionListener(this,main,name));
        add(submit);
        add(name);
    }




}
