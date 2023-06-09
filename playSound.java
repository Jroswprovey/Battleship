import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class playSound {
    private static Clip clip;

    public static void playBomb() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File soundFile = new File("C:\\Users\\zowie\\Documents\\GitHub\\Battleship\\Sounds/bomb.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    public static void stopPlayback() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
