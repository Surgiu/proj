package view;

import javax.swing.*;

public class ChessboardFrame extends JFrame{
    private static JLabel chessboard;
    //private JLabel chessBoard;
    public ChessboardFrame(){
        this.setBounds(520,60,635,935);
        this.setTitle("斗兽棋");
        //清空默认局部方式
        this.setLayout(null);
        //不能改变窗口大小
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chessboard=new JLabel(new ImageIcon("image/chessboard.png"));
        chessboard.setBounds(0,0,623,900);
        this.getContentPane().add(chessboard);
    }

    public static void main(String[] args) {
        ChessboardFrame chessboardFrame=new ChessboardFrame();
        chessboardFrame.setVisible(true);

//        JButton jb1=new JButton("单人模式");
////        单人模式含”联网对决“（online mode)【用户名账号】  ”人机对决“（AI）
//        JButton jb2=new JButton("双人模式");
//        jb1.setBounds(55,40,160,60);
//        jb2.setBounds(55,130,160,60);
////        jb2.setEnabled(true);
////        jb2.setBorderPainted(true)
////        这两句不写感觉没区别
//        enter.add(jb1);
//        enter.add(jb2);
    }
}