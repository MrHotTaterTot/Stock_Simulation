import javax.swing.*;
import java.util.ArrayList;

public class Portfolio extends NewWindow{
    JMenuBar menu;
    JMenu stocksMenu,portfolio, resize;
    ArrayList<JMenu> MenuList = new ArrayList<>();
    ArrayList<JLabel> QuantityLabels = new ArrayList<>();
    public Portfolio(Main main, String name){
        super(main, name);
        setFrame();
        setMenu();
    }

    //Sets the information about the stocks in the Portfolio window
    @Override
    public void setInterface() {
        int count = 0;
        int y = 20;
        int x = 350;
        int width = 200;
        int height = 50;
        for (Stock stock : main.StockList) {
            JLabel value = new JLabel(String.valueOf(stock.getValue()));
            value.setBounds(x, y, width, height);
            JLabel name = new JLabel(stock.getName());
            name.setBounds(x + width, y, width, height);
            JLabel symbol = new JLabel(stock.getSymbol());
            symbol.setBounds(x + (2 * width), y, width, height);
            JLabel quantity = new JLabel(String.valueOf(stock.getQuantity()));
            quantity.setBounds(x+(3*width),y,width,height);
            QuantityLabels.add(quantity);
            frame.add(QuantityLabels.get(count));
            frame.add(value);
            frame.add(name);
            frame.add(symbol);
            y += height + 60;
            count++;
        }
        SwingUtilities.updateComponentTreeUI(frame);
    }
    //Sets the actual window where the Information will be shown to the user
    @Override
    public void setFrame() {
        frame = new JFrame("Portfolio");
        frame.setLayout(null);
        frame.setSize(1200,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowCloser(frame,main.IO,main.StockList,main));
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
    }
    //Changes the Visibility of the window
    public void setVisible(boolean visible){
        frame.setVisible(visible);
    }

    //Sets the menu bar at the top of the window
    public void setMenu() {
        menu = new JMenuBar();
        stocksMenu = new JMenu("Stocks");
        stocksMenu.addMenuListener(new ActionListenerMenu(main,this, portfolio, stocksMenu));
        MenuList.add(stocksMenu);
        portfolio = new JMenu("Portfolio");
        portfolio.addMenuListener(new ActionListenerMenu(main,this, portfolio, stocksMenu));
        MenuList.add(portfolio);
        resize = new JMenu("Resize");
        MenuList.add(resize);

        menu.add(stocksMenu);menu.add(portfolio);menu.add(resize);
        frame.add(menu);
        frame.setJMenuBar(menu);

    }
    //Once buy or sell is clicked the quantity label will be refreshed
    //So it shows the actual amount of stocks the user owns
    public void refreshQuantityLabel(){
        int count = 0;
        for(Stock x : main.StockList){
            QuantityLabels.get(count).setText(String.valueOf(x.getQuantity()));
            count++;
        }
    }
}
