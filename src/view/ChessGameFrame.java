package view;

import controller.GameController;
import model.Chessboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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
    private static JLabel enter;

    public ChessGameFrame(int width, int height) {
        setTitle("2023 CS109 Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);


        addChessboard();
        addBaseLabel();
        addLabel();
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
        chessboardComponent = new ChessboardComponent(ONE_CHESS_SIZE);
        chessboardComponent.setLocation(HEIGTH / 5, HEIGTH / 10);
        add(chessboardComponent);
    }

    /**
     * 在游戏面板中添加标签
     */
    private void addLabel() {
        JLabel statusLabel = new JLabel("Sample label");
        statusLabel.setLocation(HEIGTH, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel);
    }

    public void addBaseLabel(){
        JLabel chessboard=new JLabel(new ImageIcon("image/chessboard.png"));
        chessboard.setBounds(102,-48,623,900);
//        this.getContentPane().add(chessboard);
        add(chessboard);
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
//        jb1.setBounds(HEIGTH, HEIGTH / 10 ,200, 60);
        jb2.setBounds(HEIGTH, HEIGTH / 10 + 100,200, 60);
        jb3.setBounds(HEIGTH, HEIGTH / 10 + 200,200, 60);
        jb4.setBounds(HEIGTH, HEIGTH / 10 + 300,200, 60);
        jb5.setBounds(HEIGTH, HEIGTH / 10 + 400,200, 60);

        JMenuBar bar=new JMenuBar();
        bar.setBounds(HEIGTH, HEIGTH / 10 ,200, 60);
        JMenu menu=new JMenu("菜单1");
        JMenuItem item1 = new JMenuItem("⼆级菜单1");
        JMenuItem item2 = new JMenuItem("⼆级菜单2");
        JMenuItem item3 = new JMenuItem("⼆级菜单3");
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        jb1.add(menu);

//        jb1.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Hello, world!"));

        jb5.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessGameFrame mainFrame=new ChessGameFrame(1100,810);
//                mainFrame.dispose();
                mainFrame.setVisible(false);
                EnterFrame enterFrame =new EnterFrame();
                enterFrame.setVisible(true);
            }
        });

//        jb1.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(jb1);
        add(jb2);
        add(jb3);
        add(jb4);
        add(jb5);
    }

//    private void addLoadButton() {
//        JButton button = new JButton("Load");
//        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
//        button.setSize(200, 60);
//        button.setFont(new Font("Rockwell", Font.BOLD, 20));
//        add(button);
//
//        button.addActionListener(e -> {
//            System.out.println("Click load");
//            String path = JOptionPane.showInputDialog(this,"Input Path here");
//            gameController.loadGameFromFile(path);
//        });
//    }


}
