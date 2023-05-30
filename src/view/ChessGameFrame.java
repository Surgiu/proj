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
    private JLabel statusLabel;
    private JLabel chessboard;
    private User user;
    private static JLabel picture1 = new JLabel(new ImageIcon("image/picture1.png"));
    private static JLabel picture2 = new JLabel(new ImageIcon("image/picture2.png"));
    boolean changePicture = false;
    boolean changeBackground = false;

    public ChessGameFrame(int width, int height) {
        setTitle("斗兽棋"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
//        setLayout(new BorderLayout());
        setLayout(null);

        addLabel();
        addChessboard();
        addButton();
        addBaseLabel();
        addChangePicture();
        addChangeBackground();
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
//        String text = "当前玩家:   蓝方" + "\n      回合数: 0" + "\n       剩余时间: 30s(等待玩家准备···)";
        String text = "<html> 当前玩家：蓝方<br/> 回合数：0<br/>剩余时间：60s<br/></html>";
        statusLabel = new JLabel(text);
        statusLabel.setOpaque(true);
        statusLabel.setBounds(880, 25, 160, 100);
        statusLabel.setForeground(new Color(67, 79, 173));
//        statusLabel.setForeground(new Color(190,230,233));
        statusLabel.setBackground(new Color(177, 183, 245));
        statusLabel.setFont(new Font("MS Song", Font.BOLD, 17));
        add(statusLabel);
    }


    public void addBaseLabel() {
        chessboard = new JLabel(new ImageIcon("image/chessboard.png"));
        chessboard.setBounds(102, -46, 623, 900);
        add(chessboard);
        picture1.setBounds(-134, -12, 1100, 810);
        picture2.setBounds(-134, -12, 1100, 810);
        add(picture1);
        add(picture2);
    }

    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */
    public void addChangePicture() {
        changePicture = !changePicture;
        if (changePicture == false) {
            picture1.setVisible(false);
            picture2.setVisible(true);
        } else if (changePicture == true) {
            picture1.setVisible(true);
            picture2.setVisible(false);
        }
    }

    public void addChangeBackground() {
        changeBackground = !changeBackground;
        if (changeBackground == false) {
            ChessGameFrame.this.getContentPane().setBackground(Color.BLACK);
        } else if (changeBackground == true) {
            ChessGameFrame.this.getContentPane().setBackground(Color.white);
        }
    }

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

        jb1.setBounds(HEIGTH + 70, HEIGTH / 10 + 65, 160, 45);
        jb2.setBounds(HEIGTH + 70, HEIGTH / 10 + 135, 160, 45);
        jb3.setBounds(HEIGTH + 70, HEIGTH / 10 + 205, 160, 45);
        jb4.setBounds(HEIGTH + 70, HEIGTH / 10 + 275, 160, 45);
        jb6.setBounds(HEIGTH + 70, HEIGTH / 10 + 345, 160, 45);
        jb7.setBounds(HEIGTH + 70, HEIGTH / 10 + 415, 160, 45);
        jb8.setBounds(HEIGTH + 70, HEIGTH / 10 + 485, 160, 45);
        jb9.setBounds(HEIGTH + 70, HEIGTH / 10 + 555, 160, 45);
        jb5.setBounds(HEIGTH + 70, HEIGTH / 10 + 625, 160, 45);
//        jb1.setBounds(HEIGTH + 70, HEIGTH / 10 + 85, 160, 50);
//        jb2.setBounds(HEIGTH + 70, HEIGTH / 10 + 160, 160, 50);
//        jb3.setBounds(HEIGTH + 70, HEIGTH / 10 + 235, 160, 50);
//        jb4.setBounds(HEIGTH + 70, HEIGTH / 10 + 310, 160, 50);
//        jb6.setBounds(HEIGTH + 70, HEIGTH / 10 + 385, 160, 50);
//        jb7.setBounds(HEIGTH + 70, HEIGTH / 10 + 460, 160, 50);
//        jb8.setBounds(HEIGTH + 70, HEIGTH / 10 + 535, 160, 50);
//        jb5.setBounds(HEIGTH + 70, HEIGTH / 10 + 610, 160, 50);
//        jb9.setBounds(HEIGTH + 70, HEIGTH / 10 + 685, 160, 50);

        jb1.setBackground(new Color(164, 168, 208, 255));
        jb3.setBackground(new Color(164, 168, 208, 255));
        jb5.setBackground(new Color(164, 168, 208, 255));
        jb6.setBackground(new Color(164, 168, 208, 255));
        jb8.setBackground(new Color(164, 168, 208, 255));
        jb2.setBackground(new Color(198, 204, 255, 255));
        jb4.setBackground(new Color(198, 204, 255, 255));
        jb7.setBackground(new Color(198, 204, 255, 255));
        jb9.setBackground(new Color(198, 204, 255, 255));

        jb1.setForeground(new Color(213, 242, 255));
        jb3.setForeground(new Color(213, 242, 255));
        jb5.setForeground(new Color(213, 242, 255));
        jb6.setForeground(new Color(213, 242, 255));
        jb8.setForeground(new Color(213, 242, 255));

        jb2.setForeground(new Color(255, 255, 255));
        jb4.setForeground(new Color(255, 255, 255));
        jb7.setForeground(new Color(255, 255, 255));
        jb9.setForeground(new Color(255, 255, 255));


        jb1.setFont(new Font("MS Song", Font.BOLD, 18));
        jb2.setFont(new Font("MS Song", Font.BOLD, 18));
        jb3.setFont(new Font("MS Song", Font.BOLD, 18));
        jb4.setFont(new Font("MS Song", Font.BOLD, 18));
        jb5.setFont(new Font("MS Song", Font.BOLD, 18));
        jb6.setFont(new Font("MS Song", Font.BOLD, 18));
        jb7.setFont(new Font("MS Song", Font.BOLD, 18));
        jb8.setFont(new Font("MS Song", Font.BOLD, 18));
        jb9.setFont(new Font("MS Song", Font.BOLD, 18));

        add(jb1);
        add(jb2);
        add(jb3);
        add(jb4);
        add(jb5);
        add(jb6);
        add(jb7);
        add(jb8);
        add(jb9);


        jb1.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                SettingGameFrame settingGameFrame = new SettingGameFrame(this);
                settingGameFrame.setVisible(true);
            });
        });
//        jb1.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Hello, world!"));
        jb2.addActionListener(new AbstractAction() {//regret
            @Override
            public void actionPerformed(ActionEvent e) {
                chessboardComponent.getGameController().undo();
            }
        });
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoseFrame loseFrame = new LoseFrame(chessboardComponent);
                loseFrame.setVisible(true);
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
        String initial = "<html> 当前玩家：蓝方<br/> 回合数：0<br/>剩余时间：30s<br/></html>";
        if (getChessboardComponent().getGameController() != null) {
            int t = getChessboardComponent().getGameController().getModel().getNum();
            if (t == 0) {
                statusLabel.setText(initial);
            }
        }
        statusLabel.setText(string);
    }

    public JLabel getPicture1() {
        return picture1;
    }

    public JLabel getPicture2() {
        return picture2;
    }
}
