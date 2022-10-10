import java.io.*;
import java.util.ArrayList;

public class IO_System {
    public IO_System(){}



    //Creates the stock out of a line from the file
    public Stock CreateStock(String path, int WantedLine) {
        int lines = 0;
        File file = new File(path);
        String[] words = new String[4];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for (String line = reader.readLine(); line != null ; line = reader.readLine()) {

                if (lines == WantedLine){
                    words = line.split(", ");
                }
                lines++;
            }
            reader.close();
            return new Stock(Double.parseDouble(words[0]),words[1] ,words[2], Integer.parseInt(words[3]));


        } catch(IOException e){
            System.out.println("Either the operation failed or There is not file with that name");
        }
        return null;
    }
    //Saves the information about the stocks as they have been changed
    public void SaveStockData(String path, ArrayList<Stock> Stocklist) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));
            for (Stock x: Stocklist ) {
                writer.write(x.getValue() + ", " + x.getName() + ", " + x.getSymbol() + ", " + x.getQuantity() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Either the operation failed or There is not file with that name");
        }
    }

    //Saves the information about the stocks the user had when closing
    public void SaveUserStocks(String path, ArrayList<Stock> stocklist){
        try{
            File userFile = new File(path);
            userFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(userFile, false));
            for(Stock x : stocklist) {
                writer.write(x.getValue() + ", " + x.getName() + ", " + x.getSymbol() +", "+ x.getQuantity() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Operation failed");
        }
    }

    //Saves the information about the user account (Name and balance) for the next Use
    public void SaveUserData(String path, String name, double balance){
        try{
            File userFile = new File(path);
            userFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(userFile, false));
            writer.write(name + "\n");
            writer.write(balance + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Operation failed");
        }
    }
    //Load the user data such as the name or the balance of the user
    // has only 2 data information so no controlled loop is required and there is only one user
    public User LoadUserData(String path){
        try{
            File userFile = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(userFile));
            User user = new User(reader.readLine(), Double.parseDouble(reader.readLine()));
            return user;
        } catch (IOException e) {
            System.out.println("Either the operation failed or There is not file with that name");
        }
        return null;
    }
    //Checks if the file with that name already exists
    public boolean CheckUserExistence(String path){
        try{
            File userFile = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(userFile));
            return true;

        }catch (FileNotFoundException e) {
            return false;
        }
    }

    //Loads the Stocks of the user from the external file, Used for existing users
    public ArrayList<Stock> LoadUserStocks(String path){
        ArrayList<Stock> stocks = new ArrayList<>();
        String[] stockData;
        try{
            File userFile = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(userFile));
            for(int i = 0;i < 6; i++) {
                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    stockData = line.split(", ");
                    stocks.add(new Stock(Double.parseDouble(stockData[0]), stockData[1], stockData[2], Integer.parseInt(stockData[3])));
                }
            }
            return stocks;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stocks;
    }

    //Gets the last used date from the external file Date.txt
    public String getDate(String path){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            return reader.readLine();
        } catch(IOException e){
            e.printStackTrace();
            return "Error";
        }
    }

    //Saves the current date in the file for the next use
    public void saveDate(String date, String path){
        try{
            File dateFile = new File(path);
            dateFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(dateFile));
            writer.write(date);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
