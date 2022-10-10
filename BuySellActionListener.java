import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuySellActionListener implements ActionListener {
    //Button Listener for both buy and sell buttons in the main menu
    Main main;
    JButton buy;
    JButton sell;
    Stock stock;
    public BuySellActionListener(Main main, JButton buy, JButton sell, Stock stock){
        this.buy = buy;
        this.sell = sell;
        this.main = main;
        this.stock = stock;
    }

    public void actionPerformed(ActionEvent e) {
        //Checks if the button source of the button is the same as
        //the original one that was given when created
        //For loop is run to correctly increment/decrement the stock for the User
        if(e.getSource()==buy){
            for(Stock x : main.user.getStocks()) {
                if (main.user.getBalance() >= stock.getValue() && x.getSymbol().equals(stock.getSymbol())) {
                    x.incrementQuantity();
                    main.user.setBalance(main.user.getBalance() - stock.getValue());
                    main.changeBalanceLabel();
                    main.InterfaceError.setText("");
                    main.Portfolio.refreshQuantityLabel();
                    break;
                } else if(main.user.getBalance() < stock.getValue() && x.getSymbol().equals(stock.getSymbol())) {
                    main.InterfaceError.setText("Insufficient funds");
                }
            }
        }
        if(e.getSource() == sell){
            for(Stock x : main.user.getStocks()){
                if(x.getSymbol().equals(stock.getSymbol()) && x.getQuantity() > 0){
                    x.decrementQuantity();
                    main.user.setBalance(main.user.getBalance() + stock.getValue());
                    main.changeBalanceLabel();
                    main.InterfaceError.setText("");
                    main.Portfolio.refreshQuantityLabel();
                } else if(x.getSymbol().equals(stock.getSymbol()) && x.getQuantity() <= 0){
                    main.InterfaceError.setText("You don't own the stock");
                }
            }
        }
    }
}
