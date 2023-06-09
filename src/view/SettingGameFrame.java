package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.Serial;


public class SettingGameFrame extends JFrame {
    JButton jb1;
    JButton jb2;
    JButton jb3;
    JButton jb4;
    JButton jb7;
    ChessGameFrame chessGameFrame;

    public SettingGameFrame(ChessGameFrame chessGameFrame) {
        this.setTitle("设置");
        this.setBounds(715, 200, 290, 385);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
        this.chessGameFrame = chessGameFrame;

        JLabel background = new JLabel(new ImageIcon("image/background.png"));
        background.setBounds(0, 0, 277, 450);
        this.add(background);

        jb1 = new JButton("规则");
        jb2 = new JButton("音乐");
        jb3 = new JButton("日/夜主题切换");
        jb4 = new JButton("背景切换");
        jb7 = new JButton("返回");


        jb1.setBounds(65, 40, 160, 30);
        jb2.setBounds(65, 100, 160, 30);
        jb3.setBounds(65, 160, 160, 30);
        jb4.setBounds(65, 220, 160, 30);
        jb7.setBounds(65, 280, 160, 30);

        jb1.setForeground(new Color(185, 249, 255));
        jb2.setForeground(new Color(185, 249, 255));
        jb3.setForeground(new Color(185, 249, 255));
        jb4.setForeground(new Color(185, 249, 255));
        jb7.setForeground(new Color(185, 249, 255));

        jb1.setFont(new Font("MS Song", Font.BOLD, 13));
        jb2.setFont(new Font("MS Song", Font.BOLD, 13));
        jb3.setFont(new Font("MS Song", Font.BOLD, 13));
        jb4.setFont(new Font("MS Song", Font.BOLD, 13));
        jb7.setFont(new Font("MS Song", Font.BOLD, 13));

        jb1.setBackground(new Color(238, 180, 194));
        jb2.setBackground(new Color(238, 163, 193));
        jb3.setBackground(new Color(238, 149, 182));
        jb4.setBackground(new Color(229, 134, 170));
        jb7.setBackground(new Color(220, 122, 165));

        background.add(jb1);
        background.add(jb2);
        background.add(jb3);
        background.add(jb4);
        background.add(jb7);

        jb1.addActionListener((e) -> JOptionPane.showMessageDialog(this, """
                《斗兽棋》是一款棋类游戏，分两方对战，红蓝双方各持有8个棋子隔河相对，通过移动棋子并吃掉另一方所有棋子或者占领对方巢穴即视为胜利。

                一、棋盘介绍
                棋盘呈横七列，纵九行分布。同时双方在底线设有三个陷阱，以品字排列在巢穴附近。棋盘中部有两片水域，一般称之为小河。

                二、棋子介绍
                （一）棋子分布
                《斗兽棋》棋子共有十六个，双方各持八只一样的棋子，分别是象、狮、虎、豹、狼、狗、猫、鼠。

                （二）棋子走法
                1、每个棋子一次只能走一方格，除了己方巢穴和小河之外，前后左右均可（该条规则适用于除了狮、虎、鼠之外的其它棋子，因为这三者还有特殊走法，下文会继续介绍）；
                2、老鼠过河：老鼠可以进入河里，但是在河里也只能一格一格移动；
                3、狮虎跳河：狮、虎可以跳过小河，但是如果对方老鼠在河里，把跳的路线阻隔就不能跳，若对岸是对方比自己战斗力强的兽，也不可以跳过小河。
                三、玩法规则
                （一）大小判定
                1、按照战斗力强弱排列为：象>狮>虎>豹>狼>狗>猫>鼠，但是鼠能吃象，象不能吃鼠；
                2、相同的棋可以互吃；
                3、敌兽走入本方陷阱，本方的任意兽类都可以吃去陷阱里的棋子。

                （二）胜负规则
                攻占对方巢穴或者消灭对方所有棋子，则视为胜利。
                """));

        jb2.addActionListener(new AbstractAction() {
            @Serial
            private static final long serialVersionUID = -7817683151677238573L;

            @Override
            public void actionPerformed(ActionEvent e) {
                MusicFrame musicFrame = new MusicFrame();
                musicFrame.setVisible(true);
            }
        });
        jb3.addActionListener(e -> SwingUtilities.invokeLater(() -> this.chessGameFrame.addChangeBackground()));
        jb4.addActionListener(e -> SwingUtilities.invokeLater(() -> this.chessGameFrame.addChangePicture()));
        jb7.addActionListener(new AbstractAction() {
            @Serial
            private static final long serialVersionUID = -7231196366098858164L;

            @Override
            public void actionPerformed(ActionEvent e) {
                SettingGameFrame.this.dispose();
            }
        });
    }
}
