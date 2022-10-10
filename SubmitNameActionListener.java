import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class SubmitNameActionListener implements ActionListener {
    //Action Listener for the Username submission (NewUser / ExistingUser)
    //Holds the information and sets the basic constructor
    TextField field;
    Main main;
    public SubmitNameActionListener(Main main, TextField field){
        this.main = main;
        this.field = field;
    }
    public abstract void actionPerformed(ActionEvent e);
}
