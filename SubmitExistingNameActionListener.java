import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SubmitExistingNameActionListener extends SubmitNameActionListener {
    //Action Listener for the button to submit the name of the user
    ExistingUser window;
    public SubmitExistingNameActionListener(ExistingUser window, Main main, TextField field){
        super(main,field);
        this.window = window;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //First checks if the file exists then proceeds to set the user with its information
        if(main.IO.CheckUserExistence(field.getText() + ".txt")){
            main.setUser(main.IO.LoadUserData(field.getText() + ".txt")); //Loads the user information (name,balance)
            main.createNewUserOnInterface();
            main.user.LoadUserStocks(main.IO);  //Loads stocks that user had before
            main.changeBalanceLabel();  //Updates the balance label as the balance was updated
            main.setVisible(true);
            main.Portfolio.setInterface();  //Finally, now that the user stocks are in we can set interface of Portfolio
            window.setVisible(false);
        }else{  //Otherwise, if the file doesn't exist it shows the error on the window
            JLabel error = new JLabel("This user doesn't exist in the files");
            error.setBounds(50,125,250,50);
            window.add(error);
            SwingUtilities.updateComponentTreeUI(window.getFrame());  //This just updates the frame so that the error can be visible
        }
    }
}
