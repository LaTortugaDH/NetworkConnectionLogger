import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.TimerTask;

public class DataEvaluator extends TimerTask {
    @Override
    public void run() {
        try{
            LocalDate localDate = LocalDate.now();
            FileInputStream fstream = new FileInputStream("/Users/lscharlemann/Documents/logs/ConnectionLog_"+localDate+".log");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            int errorCount=0;
            /* read log line by line */
            while ((strLine = br.readLine()) != null)   {
               if(strLine.contains("ERROR"))
               {
                   errorCount++;
                   System.out.println ("Errors found: "+errorCount);
               }
            }
            // -------------------------------------------------------------------------------------------------------------------------
            //ToDo write a log for the errorcount for every hour to display in a chart
            // -------------------------------------------------------------------------------------------------------------------------

            fstream.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
