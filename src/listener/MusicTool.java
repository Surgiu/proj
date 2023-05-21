package listener;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class MusicTool {
    private File fileBgm1;
    private File fileBgm2;
//    private File fileBgm3;
    private AudioClip acBgm1;
    private AudioClip acBgm2;
//    private AudioClip acBgm3;
    public MusicTool(){
        fileBgm1=new File("music/hug.wav");
        fileBgm2=new File("music/earth.wav");
//        fileBgm3=new File("music/happy.wav");
        try {
            acBgm1= Applet.newAudioClip(fileBgm1.toURI().toURL());
            acBgm2= Applet.newAudioClip(fileBgm2.toURI().toURL());
//            acBgm3= Applet.newAudioClip(fileBgm3.toURI().toURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loopBgm1(){
        acBgm1.loop();
    }
    public void loopBgm2(){
        acBgm2.loop();
    }
//    public void loopBgm3(){
//        acBgm3.loop();
//    }
    public void stop(){
        acBgm1.stop();
        acBgm2.stop();
//        acBgm3.stop();
    }
}

