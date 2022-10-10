import java.text.DecimalFormat;

public class Stock {
    //Stock object, Holds information like value of it, it's name or the unique symbol
    // EDIT: Now also holds the quantity of the stock mainly for user purposes
    private double value;
    private final String name;
    private final String symbol;
    private int quantity;

    public Stock(double value, String name, String symbol, int quantity){
        this.value = value;
        this.name = name;
        this.symbol = symbol;
        this.quantity = quantity;
    }

    //General methods to get information about the stock
    public String getName(){return this.name;}
    public String getSymbol(){return this.symbol;}
    public double getValue(){return this.value;}
    public void setValue(double value) {this.value = value;}
    public int getQuantity(){return this.quantity;}
    public void incrementQuantity(){this.quantity++;}
    public void decrementQuantity() {this.quantity--;}

    //Rounds the value of the stock to 2 decimal places as when changed by the function it could get to 10 places
    // and it looks bad, no one wants a bad looking code
    public void roundValue(){
        DecimalFormat format = new DecimalFormat("###.##");
        this.value = Double.parseDouble(format.format(this.value));
    }



}
