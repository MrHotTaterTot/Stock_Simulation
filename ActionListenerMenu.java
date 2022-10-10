import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class ActionListenerMenu implements MenuListener {
    // Listener for the menubar of the window
    // Because it implements the interface it is required it also
    // implements all the methods but those are not used in the program
    // therefore are empty
    private Main main;
    private Portfolio portfolio;
    private JMenu port;
    private JMenu stocks;
    public ActionListenerMenu(Main main, Portfolio portfolio, JMenu Port, JMenu stocks){
        this.main = main;
        this.portfolio = portfolio;
        this.port = Port;
        this.stocks = stocks;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        if(e.getSource() == port){
            portfolio.setVisible(true);
            main.setVisible(false);
        }
        if(e.getSource() == stocks){
            portfolio.setVisible(false);
            main.setVisible(true);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
