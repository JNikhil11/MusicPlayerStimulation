import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private final String logFile = "playback_history.txt";//created the File

    public void logSong(String songTitle) {
        try (FileWriter fw = new FileWriter(logFile, true)) {//used Append
            fw.write("Played: " + songTitle + " at " + LocalDateTime.now() + "\n");//Stores with date and Time
        } catch (IOException e) {
            System.out.println("❌ Error writing to log file: " + e.getMessage());
        }
    }
}

