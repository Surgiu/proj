package view;

import controller.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
//zhushi

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    private final int ONE_CHESS_SIZE;
    private ChessboardComponent chessboardComponent;
    private static JFrame enterFrame;
    private JLabel statusLabel;
    private static JLabel picture;
    private JLabel chessboard;
    private User user;
    private WinFrame winFrame;


    public ChessGameFrame(int width, int height) {
        setTitle("2023 CS109 Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(new BorderLayout());
        addLabel();
        addChessboard();
        addBaseLabel();
        addButton();
    }

    public ChessboardComponent getChessboardComponent() {
        return chessboardComponent;
    }

    public void setChessboardComponent(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }

    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        chessboardComponent = new ChessboardComponent(this, ONE_CHESS_SIZE);
        chessboardComponent.setLocation(HEIGTH / 5, HEIGTH / 10);
        add(chessboardComponent);
    }

    /**
     * 在游戏面板中添加标签
     */
    private void addLabel() {
        String text = "当前玩家:   蓝方" + "\n      回合数: 0" + "\n       剩余时间: 30s(等待玩家准备···)";
        statusLabel = new JLabel(text);
        statusLabel.setVisible(true);
//        JLabel statusLabel = new JLabel("当前玩家：\n"+"回合数：\n"+"下棋时间：");
        statusLabel.setBounds(877, 30, 160, 100);
        statusLabel.setForeground(Color.MAGENTA);
//        statusLabel.setForeground(new Color(190,230,233));
        statusLabel.setBackground(new Color(177, 183, 245, 255));
        statusLabel.setFont(new Font("MS Song", Font.PLAIN, 25));
        add(statusLabel, BorderLayout.NORTH);
    }


    public void addBaseLabel() {
        chessboard = new JLabel(new ImageIcon("image/chessboard.png"));
        chessboard.setBounds(102, -46, 623, 900);
//        this.getContentPane().add(chessboard);
        add(chessboard);
        picture = new JLabel(new ImageIcon("image/picture1.png"));
        picture.setBounds(0, 0, 1100, 810);
        add(picture);
    }

    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addButton() {
        JButton jb1 = new JButton("设置");
        JButton jb2 = new JButton("悔棋");
        JButton jb3 = new JButton("认输");
        JButton jb4 = new JButton("再来一局");
        JButton jb5 = new JButton("返回主页");
        JButton jb6 = new JButton("存档");
        JButton jb7 = new JButton("清空存档");
        JButton jb8 = new JButton("载入存档");
        JButton jb9 = new JButton("棋局回放");

        jb1.setBounds(HEIGTH + 70, HEIGTH / 10 + 85, 160, 50);
        jb2.setBounds(HEIGTH + 70, HEIGTH / 10 + 160, 160, 50);
        jb3.setBounds(HEIGTH + 70, HEIGTH / 10 + 235, 160, 50);
        jb4.setBounds(HEIGTH + 70, HEIGTH / 10 + 310, 160, 50);
        jb6.setBounds(HEIGTH + 70, HEIGTH / 10 + 385, 160, 50);
        jb7.setBounds(HEIGTH + 70, HEIGTH / 10 + 460, 160, 50);
        jb8.setBounds(HEIGTH + 70, HEIGTH / 10 + 535, 160, 50);
        jb5.setBounds(HEIGTH + 70, HEIGTH / 10 + 610, 160, 50);
        jb9.setBounds(HEIGTH + 70, HEIGTH / 10 + 685, 160, 50);

        picture.add(jb1);
        picture.add(jb2);
        picture.add(jb3);
        picture.add(jb4);
        picture.add(jb5);
        picture.add(jb6);
        picture.add(jb7);
        picture.add(jb8);
        picture.add(jb9);


        jb1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingGameFrame settingGameFrame = new SettingGameFrame();
                settingGameFrame.setVisible(true);
            }
        });
//        jb1.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Hello, world!"));
        jb2.addActionListener(new AbstractAction() {//regret
            @Override
            public void actionPerformed(ActionEvent e) {
                chessboardComponent.getGameController().undo();
            }
        });
        jb6.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    chessboardComponent.getGameController().saveGame();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        jb4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessboardComponent.getGameController().restart();
            }
        });
        jb5.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                ChessGameFrame mainFrame=new ChessGameFrame(1100,810);
//                mainFrame.dispose();
                chessboardComponent.getGameController().timerEnd();
                ChessGameFrame.this.setVisible(false);
                EnterFrame enterFrame = new EnterFrame();
                enterFrame.setVisible(true);
            }
        });
        jb7.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessboardComponent.getGameController().deleteMemory();
            }
        });
        jb8.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessboardComponent.getGameController().loadMemory();
            }
        });
        jb3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessboardComponent.getGameController().surrender();
            }
        });
        jb9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessboardComponent.getGameController().playBack();
            }
        });
    }

    public void grading() {
        if (user != null) {
            user.setRank(user.getRank() + 1);
        }
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public void statusUpgrading(String string) {
        String initial = "当前玩家:   蓝方" + "\n      回合数: 0" + "\n       剩余时间: 30s(等待玩家准备···)";
        if (getChessboardComponent().getGameController() != null) {
            int t = getChessboardComponent().getGameController().getModel().getNum();
            if (t == 0) {
                statusLabel.setText(initial);
            }
        }
        statusLabel.setText(string);
    }
}
