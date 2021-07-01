import java.util.Timer;

public class main {
    public static void main(String[] args){
        Timer t = new Timer();
        TestConnection testConnection = new TestConnection();
        // This task is scheduled to run every second
        t.scheduleAtFixedRate(testConnection, 0, 1000);
    }

}
