package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.net.MalformedURLException;
public class MusicTool {
    private File fileBgm1 = new File("D:/JAVA/code/project/music/hug.WAV");
    private File fileBgm2 = new File("D:/JAVA/code/project/music/earth.WAV");
    private Clip acBgm1;
    private Clip acBgm2;
    private AudioInputStream audioInput1;
    private AudioInputStream audioInput2;

    public void MusicTool1() {
        try {
            //if(fileBgm1.exists()){
//            fileBgm1 = new File("D:/JAVA/code/project/music/hug.WAV");
            audioInput1 = AudioSystem.getAudioInputStream(fileBgm1);
            acBgm1 = AudioSystem.getClip();
            acBgm1.open(audioInput1);
            acBgm1.start();
            acBgm1.loop(Clip.LOOP_CONTINUOUSLY);
            //acBgm2.stop();
            //}else{}
        } catch (Exception ex) {
            ex.printStackTrace();
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

    }

    public void MusicTool2() {
        try {
//            fileBgm2 = new File("D:/JAVA/code/project/music/earth.WAV");
            audioInput2 = AudioSystem.getAudioInputStream(fileBgm2);
            acBgm2 = AudioSystem.getClip();
            acBgm2.open(audioInput2);
            acBgm2.start();
            acBgm2.loop(Clip.LOOP_CONTINUOUSLY);
//            acBgm1.stop();
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
    public void stop1() {
        try {
//            fileBgm1 = new File("D:/JAVA/code/project/music/hug.WAV");
            audioInput1 = AudioSystem.getAudioInputStream(fileBgm1);
            acBgm1 = AudioSystem.getClip();
            acBgm1.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void stop2(){
        try {
//            fileBgm2 = new File("D:/JAVA/code/project/music/earth.WAV");
            acBgm2 = AudioSystem.getClip();
            audioInput2 = AudioSystem.getAudioInputStream(fileBgm2);
            acBgm2.open(audioInput2);
            acBgm2.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}


