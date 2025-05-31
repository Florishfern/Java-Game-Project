import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class Music {
    private Clip nameClip;
    private File soundFile;

    public Music(String name){
        soundFile = new File(name);     
    }

    public void playMusic(boolean loop, float volume) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            nameClip = AudioSystem.getClip();
            nameClip.open(audioStream);
            setVolume(volume);
            if(loop){
                nameClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            nameClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (nameClip != null) {
            nameClip.stop();
            nameClip.flush();
            nameClip.setFramePosition(0);
        }
    }

    public void setVolume(float volume) {
        if (nameClip != null && nameClip.isOpen()) {
            FloatControl gainControl = (FloatControl) nameClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
        }
    }

    public boolean isrunning(){
        if(nameClip.isRunning())
            return true;
        else
            return false;
    }
}
