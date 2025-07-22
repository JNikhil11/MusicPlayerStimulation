import java.util.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Song> songs = SongLoader.loadSongsFromFile("playlist.txt");
        if (songs.isEmpty()) {
            System.out.println("⚠️ No songs found. Please check 'playlist.txt'");
            return;
        }

        MusicPlayer player = new MusicPlayer(songs);
        player.start();

        while (true) {
            System.out.println("\n🕹 Options: [1.pause] [2.resume] [3.next] [4.stop] [5.exit] [6.shuffle] [7.loop]");
            System.out.print("👉 Enter command: ");
            String cmd = sc.nextLine().toLowerCase();

            switch (cmd) {
                case "1":
                    player.pausePlayer();
                    System.out.println("⏸ Music paused.");
                    break;
                case "2":
                    player.resumePlayer();
                    System.out.println("▶ Music resumed.");
                    break;
                case "3":
                    player.skipSong();
                    break;
                case "4":
                    player.stopPlayer();
                    System.out.println("🛑 Music stopped.");
                    return;
                case "5":
                    player.stopPlayer();
                    System.exit(0);
                    System.out.println("Exiting....");
                    break;
                case "6":
                    player.enableShuffle();
                    break;
                case "7":
                    player.enableLoop();
                    break;
                default:
                    System.out.println("❌ Unknown command.");
            }
        }
    }
}
