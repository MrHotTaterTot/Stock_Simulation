import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TextFieldMouseListener implements MouseListener {
    //Used specifically for one text-field where user types in their name
    //Used mainly for aesthetics and cool looking
    //Implements an interface therefore has to implement all methods but most do nothing
    private TextField field;
    public TextFieldMouseListener(TextField name) {
        this.field = name;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        field.setText("");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
