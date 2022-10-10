import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main extends JFrame {
    String file = "Stocks.txt"; //Name of the must-have file that defines the stocks in the program
    IO_System IO = new IO_System(); //Adds the usable Input output system for the program
    JFrame frame;

    //Menu bar items and the bar itself
    JMenuBar menu;
    JMenu stocksMenu,portfolio, resize;
    ArrayList<JMenu> MenuList = new ArrayList<JMenu>();
    //


    ArrayList<JComponent> components = new ArrayList<JComponent>();
    ArrayList<Stock> StockList = new ArrayList<>();

    ArrayList<NewWindow> windows = new ArrayList<>();
    Portfolio Portfolio; //User portfolio window

    User user;
    StockValueChanger stockChecker = new StockValueChanger(this);
    DateChecker dateChecker = new DateChecker(IO); //has the methods to get and save the current date + get the last date the user had used the program
    JLabel userBalance; //User balance text that appears on the main window
    JLabel InterfaceError = new JLabel(""); //Error message text-field on the main window

    public Main(){
        user = new User(null,5000.0);  //Creates the basic user which will be overwritten [still required to set interfaces :( ]
        addWindows();
        setWindowVisible("startingWindow",true);
        addStocks();  //Adds the stocks to the main stockList of the program
        stockChecker.changeStockValue(StockList, dateChecker.SubtractDates());  //Changes the value of stocks as soon as they get imported from the external file
        frame = setFrame();
        setMenu();
        setInterface();
    }

    //Creates the windows and stores the in the arraylist to make less
    // of a mess with the attributes of the main
    private void addWindows() {
        windows.add(new NewUser(this, "newUser"));
        windows.add(new ExistingUser(this, "existingUser"));
        windows.add(new StartingWindow(this, "startingWindow"));
        setWindows();
    }

    //creates and modifies the main frame of the program
    public JFrame setFrame() {
        frame = new JFrame("Stocks");
        frame.setLayout(null);
        frame.setSize(1200,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
        return frame;
    }

    //Creates and modifies the menubar on the top of the window
    public void setMenu() {
        menu = new JMenuBar();
        stocksMenu = new JMenu("Stocks");
        Portfolio = new Portfolio(this, "Portfolio");
        stocksMenu.addMenuListener(new ActionListenerMenu(this,Portfolio, portfolio, stocksMenu));
        MenuList.add(stocksMenu);
        portfolio = new JMenu("Portfolio");
        portfolio.addMenuListener(new ActionListenerMenu(this,Portfolio, portfolio, stocksMenu));
        MenuList.add(portfolio);
        resize = new JMenu("Resize");
        MenuList.add(resize);  //To be implemented if I have time or will to live
        //Change position and size of every component and window in the program

        menu.add(stocksMenu);menu.add(portfolio);menu.add(resize);
        frame.add(menu);
        frame.setJMenuBar(menu);

    }

    //Adds the stocks to the stocklist from the file
    public void addStocks() {
        for (int i = 0; i < 6; i++) {
            StockList.add(IO.CreateStock("Stocks.txt",i));
        }
    }
    //Initiates the program :)
    public static void main(String[] args){
        new Main();}


    //Sets the interface of the stocks in the main window
    public void setInterface(){
        int y = 100;
        int x = 350;
        int width = 200;
        int height = 50;
        JLabel price = new JLabel("Price: ");
        price.setBounds(x,y - 80,width,height);
        JLabel Name = new JLabel("Name: ");
        Name.setBounds(x+width,y - 80,width,height);
        JLabel Symbol = new JLabel("Stock Symbol: ");
        Symbol.setBounds(x+(2*width),y - 80,width,height);

        for(Stock stock : StockList){
            JLabel value = new JLabel(String.valueOf(stock.getValue()));
            value.setBounds(x,y,width,height);
            JLabel name = new JLabel(stock.getName());
            name.setBounds(x+width,y,width,height);
            JLabel symbol = new JLabel(stock.getSymbol());
            symbol.setBounds(x+(2*width),y,width,height);
            JButton sell = new JButton("Sell");
            sell.setBounds(x+(3*width),y,(int)(0.5*width),height);
            JButton buy = new JButton("Buy");
            buy.setBounds((int)(x+(3.5*width)),y,(int)(0.5*width),height);
            buy.addActionListener(new BuySellActionListener(this,buy,sell, stock));
            sell.addActionListener(new BuySellActionListener(this,buy,sell, stock));
            components.add(value);components.add(name);components.add(symbol);components.add(sell);components.add(buy);
            for(JComponent component: components){
                frame.add(component);
            }
            y+=height+30;
        }
        frame.add(price);
        frame.add(Name);
        frame.add(Symbol);
    }


    //Adds the user information i.e. name and balance onto the main window
    //And loads the user stocks for the first time in the program
    //Also sets the stuff like InterfaceError and userbalance label on the frame
    public void createNewUserOnInterface(){
        int x = 50;
        int y = 50;
        int width = 200;
        int height = 45;
        user.LoadUserStocks(IO);
        JLabel username = new JLabel("User name: " + user.getName());
        username.setBounds(x,y,width,height);
        userBalance = new JLabel("User balance: " + user.getBalance());
        userBalance.setBounds(x,y + height,width,height);
        InterfaceError.setBounds(x,y+(2*height),width, height);
        frame.addWindowListener(new WindowCloser(frame,IO,StockList,this));
        frame.add(username);
        frame.add(userBalance);
        frame.add(InterfaceError);
        components.add(username);
        components.add(userBalance);
        components.add(InterfaceError);
        SwingUtilities.updateComponentTreeUI(frame);
    }

    //creates the file with the users name and adds the stocks inside
    public void addUserStocks(){
        user.SaveBasicStocks(IO,StockList);
    }

    //updates the balance label value once bought or sold a stock
    public void changeBalanceLabel(){
        DecimalFormat df = new DecimalFormat("###.###");
        user.setBalance(Double.parseDouble(df.format(user.getBalance())));
        userBalance.setText("User balance: " + Double.parseDouble(df.format(user.getBalance())));
    }


    //hides and shows the main window
    @Override
    public void setVisible(boolean visible){
        frame.setVisible(visible);
    }

    //Updates the user information to new
    public void setUser(User user){
        this.user = user;
    }

    //Makes the windows visible/invisible depending on the name and the boolean
    public void setWindowVisible(String name, boolean visible){
        for(NewWindow x : windows){
            if(x.name.equals(name)){
                x.setVisible(visible);
            }
        }
    }
    //Sets out the layout and interface for each of the windows in the program
    //Except portfolio because it requires additional user information
    public void setWindows(){
        for(NewWindow x : windows){
            x.setFrame();
            x.setInterface();
        }
    }

}

