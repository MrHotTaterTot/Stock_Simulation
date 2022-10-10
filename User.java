import java.util.ArrayList;

public class User {
    // User class has stocks, name of the user, balance of the account
    ArrayList<Stock> stocks = new ArrayList<>();
    private String name;
    private double balance;


    public User(String name, double balance){ //Stocks will be added later in code
        this.name = name;
        this.balance = balance;
    }


    //returns the name of the user
    public String getName(){return this.name;}
    //sets the name of the user
    public void setName(String name){ this.name = name;}

    public double getBalance(){return this.balance;}
    public void setBalance(double balance){this.balance = balance;}

    public ArrayList<Stock> getStocks(){return this.stocks;}

    //for the new user
    public void SaveBasicStocks(IO_System IO, ArrayList<Stock> StockList){IO.SaveUserStocks(getName() + " Stocks.txt", StockList);}
    //______________________________//

    //for the existing user
    //Works basically like setStocks, but from external file
    public void LoadUserStocks(IO_System IO){
        this.stocks = IO.LoadUserStocks(getName() + " Stocks.txt");
    }
    //______________________________//
    //Saves the data about the user i.e. Their stocks and their name and balance
    public void SaveUserData(IO_System IO){IO.SaveUserData(getName() + ".txt", getName(), getBalance());}
    public void SaveUserStocks(IO_System IO){IO.SaveUserStocks(getName() + " Stocks.txt", stocks);}

}
