package view;

import controller.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

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
        this.setBounds(720, 410, 324, 185);
        this.setTitle("登录");
        //清空默认局部方式
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        //不能改变窗口大小
        this.setResizable(false);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        jl1 = new JLabel("请输入用户名:");
        username = new JTextField("", 29);
        jl2 = new JLabel("请输入密码:");
        password = new JTextField("", 29);
        jp = new JPanel();
        jb1 = new JButton("登陆");
//        jb1.setBounds(0,200,70, 20);
        jb2 = new JButton("注册");
//        jb2.setBounds(90,200,70, 20);
        jb3 = new JButton("取消");
//        jb3.setBounds(180,200,70, 20);
        jb4 = new JButton("删除本地用户");
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
        jb2.addActionListener(new ActionListener() {//register
            @Override
            public void actionPerformed(ActionEvent e) {
                loadUsers();
                boolean isntExist = true;
                for (User user : users) {
                    if (username.getText().equals(user.getName())) {
                        isntExist = false;
                        break;
                    }
                }

                if (username.getText().length() != 0 && password.getText().length() != 0) {
                    if (isntExist) {
                        try {
                            users.add(new User(username.getText(), password.getText()));
                            File file0 = new File(position + "/" + username.getText());
                            ObjectOutputStream info = new ObjectOutputStream(new FileOutputStream(file0));
                            info.writeObject(users.get(users.size() - 1));
                            info.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "注册失败");
                        }
                        JOptionPane.showMessageDialog(null, "注册成功！");
                        LogInFrame.this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "用户已存在！");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "error: 用户名或密码为空");
                }
            }
        });
        jb1.addActionListener(new ActionListener() {//login
            @Override
            public void actionPerformed(ActionEvent e) {
                loadUsers();
                if (username.getText().length() != 0 && password.getText().length() != 0) {
                    String name = username.getText();
                    String psd = password.getText();
                    int count = 0;
                    for (User user : users) {
                        if (name.equals(user.getName())) {
                            if (psd.equals(user.getPsd())) {
                                JOptionPane.showMessageDialog(null, "登录成功！");
                                LogInFrame.this.dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "亲爱的" + name + "，您输入的密码不正确");
                            }
                            return;
                        } else {
                            count++;
                        }
                    }
                    if (count != 0) {
                        JOptionPane.showMessageDialog(null, "亲爱的" + name + "，您还没有注册呢~");
                    }
                }
            }
        });
        jb3.addActionListener(new AbstractAction() {//cancel
            @Override
            public void actionPerformed(ActionEvent e) {
                LogInFrame.this.setVisible(false);
            }
        });
        jb4.addActionListener(new AbstractAction() {//clear
            @Override
            public void actionPerformed(ActionEvent e) {
                users.clear();
                File file = new File("resource/UserInfo/");
                File[] files = file.listFiles();
                for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
                    files[i].delete();
                }
                JOptionPane.showMessageDialog(null, "已清除所有本地用户");
            }
        });
    }

    private void loadUsers() {
        File file = new File("resource/UserInfo/");
        File[] files = file.listFiles();
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            ObjectInputStream read = null;
            try {
                read = new ObjectInputStream(new FileInputStream(files[i]));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            User user = null;
            try {
                user = (User) read.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            users.add(user);
        }
    }
}
