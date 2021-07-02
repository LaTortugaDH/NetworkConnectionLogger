import org.joda.time.DateTime;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TestConnection extends TimerTask {
    @Override
    public void run() {

        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh=null;
        HttpURLConnection connection = null;
// -------------------------------------------------------------------------------------------------------------------------
        try {
            DateTime dt = new DateTime();
            int hours = dt.getHourOfDay();
            LocalDate localDate = LocalDate.now();
            // This block configure the logger with handler and formatter
            fh = new FileHandler("/Users/lscharlemann/Documents/logs/ConnectionLog_"+localDate+".log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
// -------------------------------------------------------------------------------------------------------------------------
            URL u;
            switch(((int)System.currentTimeMillis()%10000-(int)System.currentTimeMillis()%1000)/1000) {
                case 0:
                    u = new URL("http://www.youtube.com");
                    break;
                case 5:
                    u = new URL("http://www.facebook.com");
                    break;
                case 1:
                    u = new URL("http://www.ebay.com");
                    break;
                case 6:
                    u = new URL("http://www.apple.com");
                    break;
                case 2:
                    u = new URL("http://www.wikipedia.org");
                    break;
                case 7:
                    u = new URL("http://www.google.com");
                    break;
                case 3:
                    u = new URL("http://www.netflix.com");
                    break;
                case 8:
                    u = new URL("http://www.amazon.com");
                    break;
                case 9:
                    u = new URL("http://www.baidu.com");
                    break;
                default:
                    u = new URL("http://www.twitter.com");
            }
// -------------------------------------------------------------------------------------------------------------------------
            try {
                final URLConnection conn = u.openConnection();
                conn.connect();
                conn.getInputStream().close();
                logger.info("\n\t succesfully connected to: "+u+"\n");

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                logger.warning("###################################################################"
                                + "\n \t ERROR occured while trying to connect to "+u+ " at "+hours+" o'clock"
                                + "\n############################################################################");
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.disconnect();
            }
            fh.close();
        }
    }
}
