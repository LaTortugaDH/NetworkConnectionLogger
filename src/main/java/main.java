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
    }

}
