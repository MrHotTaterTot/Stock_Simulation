import javax.swing.*;
import java.awt.*;

public abstract class NewWindow {
    // Abstract class that is the base template for any window smaller window in the program
    // Has all the main function that allow the window to be created and to change
    // information in the program
    // Requires the subclasses to implement visual methods by themselves as every window can be different
    Main main;
    JFrame frame;
    String name;
    public abstract void setFrame();  //Visual method for frame itself (size, function on close, layout)
    public abstract void setInterface();  //Visual method for interface
    public NewWindow(Main main, String name){
        this.main = main;
        this.name = name;
    }
    public JFrame getFrame(){return this.frame;}

    //Sets the visibility of each frame of any class that inherits it
    public void setVisible(boolean visible){frame.setVisible(visible);}

    //Adds a component to the frame of the window
    public <E> void add(E component){
        if(component instanceof Component){
            frame.add((Component) component);
        }
    }
    //Has to be either generic or could be of a type JComponent
    //as there are many classes that can use that method in JSWING
    //I went with generic for style :)

}
