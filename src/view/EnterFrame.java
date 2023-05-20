package view;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EnterFrame extends JFrame {
    private static JFrame jf;
    private static JLabel enter;
    public EnterFrame(){
        this.setBounds(485,210,740,550);
        this.setTitle("斗兽棋");
        //清空默认局部方式
        this.setLayout(null);
        //不能改变窗口大小
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enter=new JLabel(new ImageIcon("image/enter.png"));
        enter.setBounds(0,0,727,515);
//        chessBoard=new JLabel(new ImageIcon("image/chessboard.png"));
//        chessBoard.setBounds(0,0,700,798);
        this.getContentPane().add(enter);
    }


    public static void main(String[] args) {
        EnterFrame enterFrame =new EnterFrame();
        enterFrame.setVisible(true);
        JButton jb1=new JButton("单人模式");
//        单人模式含”联网对决“（online mode)【用户名账号】  ”人机对决“（AI）
        JButton jb2=new JButton("双人模式");
        jb1.setBounds(55,40,160,60);
        jb2.setBounds(55,130,160,60);
//        jb2.setEnabled(true);
//        jb2.setBorderPainted(true)
//        这两句不写感觉没区别
        enter.add(jb1);
        enter.add(jb2);

        jb2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessboardFrame chessboardFrame=new ChessboardFrame();
                chessboardFrame.setVisible(true);
//                跳转的界面
//                jf.dispose();
                enterFrame.setVisible(false);
//                销毁当前界面
            }
        });
    }
}
