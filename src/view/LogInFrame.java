package view;

import controller.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;

public class LogInFrame extends JFrame implements Serializable {
    @Serial
    private static final long serialVersionUID = -5794362277352127126L;
    JLabel jl1;
    JLabel jl2;
    JPanel jp;
    JButton jb1;
    JButton jb2;
    JButton jb3;
    JButton jb4;
    JTextField username;
    JTextField password;
    String position = "resource/UserInfo";

    ArrayList<User> users = new ArrayList<>();

    public LogInFrame() {
        this.setBounds(720, 410, 240, 180);
        this.setTitle("登录");
        //清空默认局部方式
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        //不能改变窗口大小
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        jl1 = new JLabel("账号:");
        username = new JTextField("", 21);
        jl2 = new JLabel("密码:");
        password = new JTextField("", 21);
        jp = new JPanel();
        jb1 = new JButton("登陆");
//        jb1.setBounds(0,200,70, 20);
        jb2 = new JButton("注册");
//        jb2.setBounds(90,200,70, 20);
        jb3 = new JButton("取消");
//        jb3.setBounds(180,200,70, 20);
        jb4=new JButton("删除本地用户");
        add(jl1);
        add(username);
        add(jl2);
        add(password);
        add(jp);
        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);
        jp.add(jb4);
        addButtons();
    }

    public void addButtons() {
        jb2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isExist = false;
                for (User user : users) {
                    if (username.getText().equals(user.getName())) {
                        isExist = false;
                        break;
                    } else {
                        isExist = true;
                    }
                }
                if (e.getSource() == jb2) {//register
                    if (username.getText().length() != 0 && password.getText().length() != 0 && isExist) {
                        try {
                            users.add(new User(username.getText(), password.getText()));
                            File file = new File(position + "/" + username.getText());
                            ObjectOutputStream info = new ObjectOutputStream(new FileOutputStream(file));
                            info.writeObject(users.get(users.size() - 1));
                            info.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "注册失败");
                        }
                        JOptionPane.showMessageDialog(null, "注册成功！");
                        LogInFrame.this.setVisible(false);
                    }
                } else if (e.getSource() == jb1) {//login
                    if (username.getText().length() != 0 && password.getText().length() != 0) {
                        String name = username.getText();
                        String psd = password.getText();
                        for (User user : users) {
                            if (name.equals(user.getName())) {
                                if (psd.equals(user.getPsd())) {
                                    JOptionPane.showMessageDialog(null, "登录成功！");
                                }
                            }
                        }
                        LogInFrame.this.setVisible(false);
                    }
                }
            }
        });
        jb3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogInFrame.this.setVisible(false);
            }
        });
    }


}
