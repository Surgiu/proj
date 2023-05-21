package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class LogInFrame extends JFrame {
    public LogInFrame() {
        this.setBounds(720, 410, 220, 180);
        this.setTitle("登录");
        //清空默认局部方式
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        //不能改变窗口大小
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        JLabel jl1 = new JLabel("账号:");
        JTextField username = new JTextField("", 21);
        JLabel jl2 = new JLabel("密码:");
        JTextField password = new JTextField("", 21);
        JPanel jp = new JPanel();
        JButton jb1 = new JButton("登陆");
//        jb1.setBounds(0,200,70, 20);
        JButton jb2 = new JButton("注册");
//        jb2.setBounds(90,200,70, 20);
        JButton jb3 = new JButton("取消");
//        jb3.setBounds(180,200,70, 20);
        add(jl1);
        add(username);
        add(jl2);
        add(password);
        add(jp);
        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);
    }
    public void click(MouseEvent e) {

    }
}
