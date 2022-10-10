import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SubmitNewNameActionListener extends SubmitNameActionListener {
    //Action Listener for the button to submit the name of the user
    NewUser window;
    public SubmitNewNameActionListener(NewUser window, Main main, TextField field){
        super(main, field);
        this.window = window;
    }

    public void actionPerformed(ActionEvent e) {
        //Error is used for 2 occasions, so it's in the wider scope
        JLabel error = new JLabel();
        error.setBounds(50,125,250,50);
        window.add(error);
        if(field.getText().length() > 16){  //Checks the length of the name to be max 16 (Would look bad in the files)
            error.setText("Name longer than 16 characters");
            SwingUtilities.updateComponentTreeUI(window.getFrame());  //Updates the state of the frame
            return;  // Just so nothing else is checked as there was an error
        }
        if(main.IO.CheckUserExistence(field.getText() + ".txt")){ //Cheks if the file already exists, because that would lead to overwriting files or multiplication (error)
            error.setText("This username is already taken");
            SwingUtilities.updateComponentTreeUI(window.getFrame()); // Updates the framew

        }else {  //Once both conditions were met user can be created in the main window
            main.user.setName(field.getText());
            main.addUserStocks();
            main.createNewUserOnInterface();
            main.setVisible(true);
            main.Portfolio.setInterface();
            window.setVisible(false);
        }
        ;
    }
}
