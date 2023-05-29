package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.Serial;

public class WinFrame extends JFrame {
    private static JLabel background;
    JButton jb1;
    JButton jb2;
    JButton jb3;


    public WinFrame(ChessboardComponent chessboardComponent) {
        this.setTitle("斗兽棋");
        this.setBounds(705, 350, 310, 200);
        this.setLayout(null);
        this.setResizable(false);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        JLabel jl = new JLabel("恭喜您，您赢了！");
        jl.setBounds(65, 30, 200, 60);
        jl.setForeground(new Color(5, 27, 93, 229));
        jl.setFont(new Font("MS Song", Font.BOLD, 22));
        add(jl);

        background = new JLabel(new ImageIcon("image/lose.png"));
        background.setBounds(0, 0, 310, 200);
        this.add(background);

        jb1 = new JButton("再来一局");
        jb2 = new JButton("返回");
        jb3 = new JButton("退出");

        jb1.setBounds(15, 110, 90, 30);
        jb2.setBounds(118, 110, 75, 30);
        jb3.setBounds(206, 110, 75, 30);

        jb1.setForeground(new Color(185, 249, 255));
        jb2.setForeground(new Color(185, 249, 255));
        jb3.setForeground(new Color(185, 249, 255));

        jb1.setFont(new Font("MS Song", Font.BOLD, 13));
        jb2.setFont(new Font("MS Song", Font.BOLD, 13));
        jb3.setFont(new Font("MS Song", Font.BOLD, 13));

        jb1.setBackground(new Color(225, 153, 219));
        jb2.setBackground(new Color(225, 153, 219));
        jb3.setBackground(new Color(225, 153, 219));

        background.add(jb1);
        background.add(jb2);
        background.add(jb3);

        jb1.addActionListener(new AbstractAction() {
            @Serial
            private static final long serialVersionUID = 4341241932262345437L;

            @Override
            public void actionPerformed(ActionEvent e) {
                chessboardComponent.getGameController().restart();
                WinFrame.this.dispose();

            }
        });
        jb2.addActionListener(new AbstractAction() {
            @Serial
            private static final long serialVersionUID = 7379875196635755159L;

            @Override
            public void actionPerformed(ActionEvent e) {
                WinFrame.this.dispose();
            }
        });
        jb3.addActionListener(e -> System.exit(0));
    }
}