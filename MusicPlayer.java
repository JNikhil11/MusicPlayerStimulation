import java.util.Collections;
import java.util.List;

public class MusicPlayer extends Thread {
    private final List<Song> playlist;
    private int currentIndex = 0;
    private boolean isRunning = true;
    private boolean isPaused = false;
    private boolean shuffleMode = false;
    private boolean loopMode = false;
    private final Object lock = new Object();
    private final Logger logger = new Logger();

    public MusicPlayer(List<Song> playlist) {
        this.playlist = playlist;
    }

    public void run() {
        while (isRunning) {
            if (currentIndex >= playlist.size()) {
                if (loopMode) {
                    currentIndex = 0;
                } else {
                    break;
                }
            }

            Song song = playlist.get(currentIndex);
            System.out.println("🎵 Now Playing: " + song.getTitle());
            logger.logSong(song.getTitle());

            for (int i = 1; i <= song.getDuration(); i++) {
                synchronized (lock) {
                    while (isPaused) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }

                System.out.println("⏱ " + i + "s");
            }

            currentIndex++;
        }

        System.out.println("🎼 Playlist Ended.");
    }

    public void pausePlayer() {
        isPaused = true;
    }

    public void resumePlayer() {
        synchronized (lock) {
            isPaused = false;
            lock.notify();
        }
    }

    public void skipSong() {
        this.interrupt();
    }

    public void stopPlayer() {
        isRunning = false;
        this.interrupt();
    }

    public void enableShuffle() {
        shuffleMode = true;
        Collections.shuffle(playlist);
        currentIndex = 0;
        System.out.println("🔀 Playlist shuffled.");
    }

    public void enableLoop() {
        loopMode = true;
        System.out.println("🔁 Loop mode enabled.");
    }
}

