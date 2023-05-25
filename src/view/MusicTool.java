package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.net.MalformedURLException;
public class MusicTool {
    private static File fileBgm1;
//    private static File fileBgm2;
    private static Clip acBgm1;
//    private static Clip acBgm2;
    private static AudioInputStream audioInput1;
//    private static AudioInputStream audioInput2;
    public static void MusicTool(){
        try {
                fileBgm1=new File("music/hug.WAV");
                acBgm1= AudioSystem.getClip();
                audioInput1=AudioSystem.getAudioInputStream(fileBgm1);
                acBgm1.open(audioInput1);
                acBgm1.start();;
                acBgm1.loop(Clip.LOOP_CONTINUOUSLY);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
//        public static void MusicTool1(){
//        try {
//            fileBgm1=new File("music/hug.wav");
//            acBgm1= AudioSystem.getClip();
//            audioInput1=AudioSystem.getAudioInputStream(fileBgm1);
//            acBgm1.open(audioInput1);
//            acBgm1.start();;
//            acBgm1.loop(Clip.LOOP_CONTINUOUSLY);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    public static void MusicTool2(){
//        try {
//            fileBgm2=new File("D:/JAVA/code/project/music/earth.WAV");
//            acBgm2= AudioSystem.getClip();
//            audioInput2=AudioSystem.getAudioInputStream(fileBgm2);
//            acBgm2.open(audioInput2);
//            acBgm2.start();;
//            acBgm2.loop(Clip.LOOP_CONTINUOUSLY);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }


//    public void loopBgm1(){
//        acBgm1.loop();
//    }
//    public void loopBgm2(){
//        acBgm2.loop();
//    }
    public void stop(){
        acBgm1.stop();
    }
//    public void stop2(){
//        acBgm2.stop();
//    }
}

