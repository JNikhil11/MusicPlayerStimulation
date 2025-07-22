import java.io.*;
import java.util.*;

public class SongLoader {

    public static List<Song> loadSongsFromFile(String fileName) {
        List<Song> songs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String title = parts[0].trim();
                    int duration = Integer.parseInt(parts[1].trim());
                    songs.add(new Song(title, duration));
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading playlist file: " + e.getMessage());
        }

        return songs;
    }
}

