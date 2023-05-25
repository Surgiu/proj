package view;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class MusicFrame extends JFrame{
    private static JLabel music;
    private boolean Bgm1Play = false;
    private boolean Bgm2Play = false;
    JButton jb1;
//    JButton jb2;
    JButton jb3;
    JButton jb4;
    public MusicFrame(){
        this.setTitle("音乐");
//        this.setBounds(715,360,290,340);
        this.setBounds(715,360,290,280);
        this.setLayout(null);
        this.setResizable(false);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        music =new JLabel(new ImageIcon("image/music.png")) ;
        music.setBounds(0,0,277,308);
        this.add(music);

        jb1 = new JButton("拥抱");
//        jb2 = new JButton("离开地球表面");
        jb3 = new JButton("关闭音乐");
        jb4 = new JButton("返回");


        jb1.setBounds(65, 40, 160, 30);
        jb3.setBounds(65, 100, 160, 30);
        jb4.setBounds(65, 160, 160, 30);
//        jb4.setBounds(65, 220, 160, 30);

        music.add(jb1);
//        music.add(jb2);
        music.add(jb3);
        music.add(jb4);

        jb1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                musicTool.MusicTool();
            }
        });
//        jb2.addActionListener(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                MusicTool musicTool=new MusicTool();
//                musicTool.MusicTool2();
//            }
//        });
        jb3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                MusicTool musicTool=new MusicTool();
                musicTool.stop();
            }
        });

        jb4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MusicFrame.this.dispose();
            }
        });

    }
    MusicTool musicTool=new MusicTool();

    public MusicTool getMusicTool() {
        return musicTool;
    }

    public void setMusicTool(MusicTool musicTool) {
        this.musicTool = musicTool;
    }
}
