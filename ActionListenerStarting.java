import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerStarting implements ActionListener {
    //Action listener for the starting window
    //that appears the first when program is run
    JButton Existing;
    JButton New;
    JFrame frame;
    Main main;
    public ActionListenerStarting(JButton New, JButton Existing, JFrame frame, Main main ){
        this.Existing = Existing;
        this.New = New;
        this.frame = frame;
        this.main = main;
        //The existing and New are buttons that are
        //pressed that are located on the starting window
        //Main and frame are the windows that minimise or appear once clicked
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Checks the source if it's equal to the original one passed when created
        //then changes visibility of the windows depending on what button was pressed
        if(e.getSource() == New){
            main.setWindowVisible("newUser", true);
        }
        if(e.getSource() == Existing){
            main.setWindowVisible("existingUser",true);
        }
        frame.setVisible(false);
    }
}
