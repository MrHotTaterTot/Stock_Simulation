import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateChecker {
    //This checks the difference in days between 2 dates given
    //First one being current the second being one from Date.txt
    IO_System IO;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public DateChecker(IO_System IO){
        this.IO = IO;
    }
    public long SubtractDates(){

        try {
            Date firstDate = format.parse(format.format(new Date()));  //Current date
            Date secondDate = format.parse(IO.getDate("Date.txt"));  //From Date.txt

            //First the difference is given in Milliseconds as it's most acurate
            long DifferenceInMilliseconds = Math.abs(secondDate.getTime() - firstDate.getTime());
            long diff = TimeUnit.DAYS.convert(DifferenceInMilliseconds, TimeUnit.MILLISECONDS);
            //then it's converted to days given the value and then the unit in the method
            return diff;
        } catch(ParseException e){
            //If there is a problem with
            e.printStackTrace();
            return 0;
        }
    }
    public void saveCurrentDate(){
        String currentDate = format.format(new Date());
        IO.saveDate(currentDate, "Date.txt");
    }


}
