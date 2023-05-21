package view;

import controller.GameController;
import model.Chessboard;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class SettingGameFrame extends JFrame {
    private static JLabel background;
    JButton jb1;
    JButton jb2;
    JButton jb7;
    JButton jb8;
    public SettingGameFrame(){
        this.setTitle("设置");
        this.setBounds(715,200,280,620);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        background =new JLabel(new ImageIcon("image/background.png")) ;
        background.setBounds(0,0,280,600);
        this.add(background);

        jb1 = new JButton("规则");
        jb2 = new JButton("音量");
        jb7 = new JButton("返回");
//        jb8 = new JButton("退出");

        jb1.setBounds(55, 40, 160, 30);
        jb2.setBounds(55, 100, 160, 30);
        jb7.setBounds(55, 160, 160, 30);
//        jb8.setBounds(55, 220, 160, 30);

        background.add(jb1);
        background.add(jb2);
        background.add(jb7);
//        background.add(jb8);

        jb1.addActionListener((e) -> JOptionPane.showMessageDialog(this, "《斗兽棋》是一款棋类游戏，分两方对战，红蓝双方各持有8个棋子隔河相对，通过移动棋子并吃掉另一方所有棋子或者占领对方巢穴即视为胜利。\n" +
                "\n" +
                "一、棋盘介绍\n" +
                "棋盘呈横七列，纵九行分布。同时双方在底线设有三个陷阱，以品字排列在巢穴附近。棋盘中部有两片水域，一般称之为小河。\n" +
                "\n" +
                "二、棋子介绍\n" +
                "（一）棋子分布\n" +
                "《斗兽棋》棋子共有十六个，双方各持八只一样的棋子，分别是象、狮、虎、豹、狼、狗、猫、鼠。\n" +
                "\n" +
                "（二）棋子走法\n" +
                "1、每个棋子一次只能走一方格，除了己方巢穴和小河之外，前后左右均可（该条规则适用于除了狮、虎、鼠之外的其它棋子，因为这三者还有特殊走法，下文会继续介绍）；\n" +
                "2、老鼠过河：老鼠可以进入河里，但是在河里也只能一格一格移动；\n" +
                "3、狮虎跳河：狮、虎可以跳过小河，但是如果对方老鼠在河里，把跳的路线阻隔就不能跳，若对岸是对方比自己战斗力强的兽，也不可以跳过小河。\n" +
                "三、玩法规则\n" +
                "（一）大小判定\n" +
                "1、按照战斗力强弱排列为：象>狮>虎>豹>狼>狗>猫>鼠，但是鼠能吃象，象不能吃鼠；\n" +
                "2、相同的棋可以互吃；\n" +
                "3、敌兽走入本方陷阱，本方的任意兽类都可以吃去陷阱里的棋子。\n" +
                "\n" +
                "（二）胜负规则\n" +
                "攻占对方巢穴或者消灭对方所有棋子，则视为胜利。\n"));

//        jb2.addActionListener(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });

        jb7.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingGameFrame.this.dispose();
            }
        });




    }
}
