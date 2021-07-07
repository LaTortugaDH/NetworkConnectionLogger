import org.joda.time.DateTime;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DataEvaluator extends TimerTask {
    @Override
    public void run() {
        FileHandler fh=null;
        LocalDate localDate = LocalDate.now();
        try{
            DateTime dt = new DateTime();
            int hours = dt.getHourOfDay();
            FileInputStream fstream = new FileInputStream("/Users/lscharlemann/Documents/logs/ConnectionLog_"+localDate+".log");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            int errorCount=0;
            // -------------------------------------------------------------------------------------------------------------------------
            /* read log line by line */
            while ((strLine = br.readLine()) != null)
            {
               if(strLine.contains("ERROR")&&strLine.contains("at "+hours))
               {
                   errorCount++;
                   System.out.println ("Errors found: "+errorCount);
               }
            }
            // -------------------------------------------------------------------------------------------------------------------------
            fh = new FileHandler("/Users/lscharlemann/Documents/logs/ErrorLog_"+localDate+".log", true);
            Logger logger = Logger.getLogger("MyErrorLog");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info("There were "+errorCount+" errors in the last hour.("+hours+"o'clock)\n");
            // -------------------------------------------------------------------------------------------------------------------------
            fstream.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }finally {
            fh.close();
        }
    }
}
