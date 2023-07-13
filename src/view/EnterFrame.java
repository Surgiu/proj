package view;

import controller.GameController;
import controller.Mode;
import model.Chessboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.Serial;

public class EnterFrame extends JFrame {
    JButton jb1;
    JButton jb2;
    JButton jb3;
    JButton jb4;
    JButton jb5;
    JButton jb6;

    public EnterFrame() {
        this.setBounds(485, 210, 740, 550);
        this.setTitle("斗兽棋");
        //清空默认局部方式
        this.setLayout(null);
        //不能改变窗口大小
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel enter = new JLabel(new ImageIcon("image/enter.png"));
        enter.setBounds(0, 0, 727, 515);
        this.add(enter);

        jb1 = new JButton("单人模式");
//        单人模式含”联网对决“（online mode)【用户名账号】  ”人机对决“（AI）
        jb2 = new JButton("双人模式");
        jb3 = new JButton("设置");
        jb4 = new JButton("退出");
        jb5 = new JButton("登录");
        jb6 = new JButton("排名");

        jb1.setBackground(new Color(94, 177, 231, 255));
        jb2.setBackground(new Color(102, 193, 211, 255));
        jb3.setBackground(new Color(105, 139, 199, 255));
        jb5.setBackground(new Color(122, 145, 196, 255));
        jb6.setBackground(new Color(146, 164, 204, 255));
        jb4.setBackground(new Color(162, 175, 206, 255));

        jb1.setForeground(new Color(255, 213, 254));
        jb2.setForeground(new Color(255, 222, 245));
        jb3.setForeground(new Color(255, 207, 245));
        jb5.setForeground(new Color(255, 217, 243));
        jb6.setForeground(new Color(255, 237, 247));
        jb4.setForeground(new Color(255, 255, 255));

        jb1.setFont(new Font("MS Song", Font.BOLD, 18));
        jb2.setFont(new Font("MS Song", Font.BOLD, 18));
        jb3.setFont(new Font("MS Song", Font.BOLD, 12));

        jb1.setBounds(55, 40, 160, 60);
        jb2.setBounds(55, 130, 160, 60);
        jb3.setBounds(650, 30, 60, 30);
        jb5.setBounds(650, 80, 60, 30);
        jb6.setBounds(650, 130, 60, 30);
        jb4.setBounds(650, 180, 60, 30);

//        jb2.setEnabled(true);
//        jb2.setBorderPainted(true);
//        这两句不写感觉没区别
        enter.add(jb1);
        enter.add(jb2);
        enter.add(jb3);
        enter.add(jb4);
        enter.add(jb5);
        enter.add(jb6);

        jb2.addActionListener(new AbstractAction() {
            @Serial
            private static final long serialVersionUID = -3780203819265131050L;

            @Override
            public void actionPerformed(ActionEvent e) {
                ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
                mainFrame.setVisible(true);
                GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard(), Mode.MULTIPLAYER);
//                跳转的界面

//                EnterFrame enterFrame = new EnterFrame();
                EnterFrame.this.setVisible(false);
//                enterFrame.dispose();
//                销毁当前界面
            }
        });
        jb1.addActionListener(new AbstractAction() {
            @Serial
            private static final long serialVersionUID = 7231678883817372043L;

            @Override
            public void actionPerformed(ActionEvent e) {
                ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
                mainFrame.setVisible(true);
                GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard(), Mode.SINGLEPLAYER);
//                跳转的界面
//                EnterFrame enterFrame = new EnterFrame();
                EnterFrame.this.setVisible(false);
//                enterFrame.dispose();
//                销毁当前界面
            }
        });

        jb3.addActionListener(new AbstractAction() {
            @Serial
            private static final long serialVersionUID = 6983539001590224473L;

            @Override
            public void actionPerformed(ActionEvent e) {
                ChessGameFrame chessGameFrame = new ChessGameFrame(1100, 810);
                SettingGameFrame settingGameFrame = new SettingGameFrame(chessGameFrame);
                settingGameFrame.setVisible(true);
            }
        });

        jb4.addActionListener(e -> System.exit(0));

        jb5.addActionListener(new AbstractAction() {
            @Serial
            private static final long serialVersionUID = 3211244547294262910L;

            @Override
            public void actionPerformed(ActionEvent e) {
                LogInFrame logInFrame = new LogInFrame();
                logInFrame.setVisible(true);
            }
        });


//    public static void main(String[] args) {
//        EnterFrame enterFrame = new EnterFrame();
//        enterFrame.setVisible(true);

//}
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == jb2) {
//            this.dispose();
//            ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
//            GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard());
//            mainFrame.setVisible(true);
//        }
    }

//     jb2.addActionListener(e -> {
//        SwingUtilities.invokeLater(() -> {
//            dispose();
//            ChessGameFrame ChessGameFrame = new ChessGameFrame(800, 1000, server, user);
//            GameController gameController = new GameController(ChessGameFrame.getChessboardComponent(), new Chessboard(), GameMode.AI_2);
//            ChessGameFrame.setVisible(true);
//            this.dispose();
//        });
//    });
}
