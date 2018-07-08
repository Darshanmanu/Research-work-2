import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

class SimplePlayer
{
    Clip clip;
    String status;
    AudioInputStream audio;
    String filepath;

    SimplePlayer(String path) throws Exception
    {
        filepath=path;
        audio= AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
        clip=AudioSystem.getClip();
        clip.open(audio);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
public class AudioPlayer {
    public static void main(String args[]) throws Exception
    {
        SimplePlayer sm=new SimplePlayer("D:\\research 2\\Algorithms\\Yahoo\\NeeMeetida-SPB-.wav");
        sm.clip.start();
    }
}
