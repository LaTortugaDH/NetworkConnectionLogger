import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimerTask;

public class CleanUp extends TimerTask {
    @Override
    public void run() {
        cleanUp("/Users/lscharlemann/Documents/logs",14);
    }
    // -------------------------------------------------------------------------------------------------------------------------
    private void cleanUp(String directoryPath, int keepDays) {

        LocalDate beginnDate = getDateXDaysAgo(keepDays);
        LocalDate today = LocalDate.now();

        File dir = new File(directoryPath);
        for (File file : dir.listFiles()) {
            if (!file.isDirectory()&&file.getName().endsWith(".log")) {
                int start = file.getName().indexOf("_")+1;
                String dateStr= file.getName().substring(start, file.getName().length()-4);
                LocalDate aDate = LocalDate.of(Integer. parseInt(dateStr.substring(0,4)),Integer. parseInt(dateStr.substring(5,7)),Integer. parseInt(dateStr.substring(8,10)));

                if (!(aDate.isEqual(beginnDate)|| aDate.isEqual(today) || (aDate.isAfter(beginnDate)&&aDate.isBefore(today)))) {
                    file.delete();
                }
            }
        }
    }
    // -------------------------------------------------------------------------------------------------------------------------
    private LocalDate getDateXDaysAgo(int days)
    {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -days);
        return cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
