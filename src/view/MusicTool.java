package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.net.MalformedURLException;

public class MusicTool {
    private static File fileBgm1;
    private static File fileBgm2;
    private static File fileBgm3;
    private static File fileBgm4;
    private static Clip acBgm1;
    private static Clip acBgm2;
    private static Clip acBgm3;
    private static Clip acBgm4;
    private static AudioInputStream audioInput1;
    private static AudioInputStream audioInput2;
    private static AudioInputStream audioInput3;
    private static AudioInputStream audioInput4;

    public static void MusicTool() {
        try {
            fileBgm1 = new File("music/hug.WAV");
            acBgm1 = AudioSystem.getClip();
            audioInput1 = AudioSystem.getAudioInputStream(fileBgm1);
            acBgm1.open(audioInput1);
            acBgm1.start();
            ;
            acBgm1.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
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
    public static void MusicTool2() {
        try {
            fileBgm2 = new File("music/plant.WAV");
            acBgm2 = AudioSystem.getClip();
            audioInput2 = AudioSystem.getAudioInputStream(fileBgm2);
            acBgm2.open(audioInput2);
            acBgm2.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //    public void loopBgm1(){
//        acBgm1.loop();
//    }
//    public void loopBgm2(){
//        acBgm2.loop();
//    }
    public void stop() {
        acBgm1.stop();
    }

    public static void MusicTool3() {
        try {
            fileBgm3 = new File("music/select.WAV");
            acBgm3 = AudioSystem.getClip();
            audioInput3 = AudioSystem.getAudioInputStream(fileBgm3);
            acBgm3.open(audioInput3);
            acBgm3.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void MusicTool4() {
        try {
            fileBgm4 = new File("music/inWater.WAV");
            acBgm4 = AudioSystem.getClip();
            audioInput4 = AudioSystem.getAudioInputStream(fileBgm4);
            acBgm4.open(audioInput4);
            acBgm4.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    public void stop2(){
//        acBgm2.stop();
//    }
}

