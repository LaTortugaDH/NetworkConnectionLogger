import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Timer;

public class main {
    public static void main(String[] args){
        Timer t = new Timer();
        TestConnection testConnection = new TestConnection();
        // This task is scheduled to run every second
        t.scheduleAtFixedRate(testConnection, 0, 1000);
        Timer t2 = new Timer();
        DataEvaluator dataEvaluator = new DataEvaluator();
        // This task is scheduled to run once every hour
        t2.scheduleAtFixedRate(dataEvaluator,0,3600000);
        Timer t3 = new Timer();
        CleanUp cleanUp = new CleanUp();
        // This task is scheduled to run once every day
        t3.scheduleAtFixedRate(cleanUp,0,86400000);
        System.out.println(args);
    }
}
