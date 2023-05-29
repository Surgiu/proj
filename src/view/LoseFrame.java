package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.Serial;
import java.io.Serializable;

public class LoseFrame extends JFrame implements Serializable {
    @Serial
    private static final long serialVersionUID = -2700339924094015175L;
    private static JLabel background;
    JButton jb1;
    JButton jb2;
    JButton jb3;

    public LoseFrame(ChessboardComponent chessboardComponent) {
        this.setTitle("斗兽棋");
        this.setBounds(705, 350, 310, 200);
        this.setLayout(null);
        this.setResizable(false);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        JLabel jl = new JLabel("很遗憾，您输了！");
        jl.setBounds(105, 40, 100, 40);
//        jl.setFont(new Font("Rockwell", Font.BOLD, 15));
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

        background.add(jb1);
        background.add(jb2);
        background.add(jb3);

        jb1.addActionListener(new AbstractAction() {
            @Serial
            private static final long serialVersionUID = -7809399524545280947L;

            @Override
            public void actionPerformed(ActionEvent e) {
                chessboardComponent.getGameController().restart();
                LoseFrame.this.dispose();
            }
        });
        jb2.addActionListener(new AbstractAction() {
            @Serial
            private static final long serialVersionUID = -4250500450613256080L;

            @Override
            public void actionPerformed(ActionEvent e) {
                LoseFrame.this.dispose();
            }
        });
        jb3.addActionListener(e -> System.exit(0));
    }
}
