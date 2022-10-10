import javax.swing.*;
import java.util.ArrayList;

public class StartingWindow extends NewWindow{
    //First window that appears when the program is run and has 1 use
    //Giving the user option to create a new user account in the program
    //or choose to use the existing one
     public StartingWindow(Main main, String name){
         super(main, name);
     }
    @Override
    public void setFrame() {
        frame = new JFrame("Trading application - Mini Project");
        frame.setLayout(null);
        frame.setSize(300,300);
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void setInterface() {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton New = new JButton("I am a new user");
        New.setBounds(70, 60, 160, 50);
        JButton Existing = new JButton("I am an existing user");
        Existing.setBounds(70, 140, 160, 50);
        buttons.add(New);
        buttons.add(Existing);
        for (JButton x: buttons) {
            frame.add(x);
        }

        New.addActionListener(new ActionListenerStarting(New, Existing, frame, this.main ));
        Existing.addActionListener(new ActionListenerStarting(New, Existing, frame, this.main ));
    }



}
