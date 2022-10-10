import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class WindowCloser extends WindowAdapter {
    private final JFrame frame;
    private final IO_System IO;
    private final String file;
    private final Main main;
    ArrayList<Stock> StockList;
    public WindowCloser(JFrame frame, IO_System IO, ArrayList<Stock> Stocklist, Main main){
        this.frame = frame;
        this.IO = IO;
        this.file = "Stocks.txt";
        this.StockList = Stocklist;
        this.main = main;
    }

    //Once the window is set to be closing program saves the user information
    //and information of the stocks and disposes of the main frame therefore ending the program
    @Override
    public void windowClosing(WindowEvent e) {
        main.user.SaveUserStocks(IO);
        main.user.SaveUserData(IO);
        IO.SaveStockData(file,StockList);
        main.dateChecker.saveCurrentDate();
        frame.dispose();
    }
}
