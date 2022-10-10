import java.util.ArrayList;
import java.util.Random;

public class StockValueChanger {
    //Function that changes the value of the given stock randomly
    //It's made of percentage of original value so this way it will never get below 0
    // Trust me on that I checked. Lowest you can get is NaN :)
    Main main;
    public StockValueChanger(Main main){
        this.main = main;
    }
    public void changeStockValue(ArrayList<Stock> stocks, long days){
        if(days > 0) {
            for (Stock x : stocks) {
                double change = 0.012;
                double final_change = 0.012;
                int delta_change_min = -10;
                int delta_change_max = 10;
                Random rand = new Random();
                for (int i = 0; i < days; i++) {
                    change = (change + (final_change * (double) (rand.nextInt(Math.abs(delta_change_max - delta_change_min)) + delta_change_min) / 10));  //Might seem long but completely hand made with no help :)
                    x.setValue(x.getValue() + x.getValue() * change);
                    if (change > 0.08) {
                        change = 0.08;
                        change = Math.sin(Math.toRadians(change * 30));
                    } else if (change < -0.08) {
                        change = -0.08;
                        change = -(Math.sin(Math.toRadians(change * 30)));
                    }
                }
                x.roundValue();  //Rounds the value of the stock as it can be very weird otherwise
            }
        }
    }
}
