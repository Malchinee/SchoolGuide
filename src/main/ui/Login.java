package main.ui;

import main.common.R;
import main.controller.UserController;
import main.entity.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Login {
    private JFrame frame = new JFrame("welcome");
    private JPanel panel = new JPanel();
    private JLabel userLabel = new JLabel("请输入账户名:");
    private JTextField userText = new JTextField();//获取账户名
    private JLabel passLabel = new JLabel("请输入密码:");
    private JPasswordField passText = new JPasswordField(20);//密码框隐藏
    private JButton loginButton = new JButton("登录");
    private JButton registerButton = new JButton("注册");
    private JTextArea jTextArea = new JTextArea("备注：账户名字母开头，仅支持小写\r\n密码必须为6位及以上\r\n");

    private UserController userController = new UserController();
    public Login() throws IOException, ClassNotFoundException {

        //设置窗体的位置及大小
        frame.setSize(600,500);
        frame.setLocationRelativeTo(null);//在屏幕中居中显示
        frame.add(panel);//添加面板
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击X号关闭
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel jPanel){
        panel.setLayout(null);

        //创建userJLabel
        userLabel.setBounds(60,60,120,25);
        panel.add(userLabel);
        //创建文本域供用户输入
        userText.setBounds(210,60,165,25);
        panel.add(userText);

        //创建passJLabel
        passLabel.setBounds(60,100,120,25);
        panel.add(passLabel);
        //创建密码输入框
        passText.setBounds(210,100,165,25);
        panel.add(passText);
        //创建登陆注册按钮
        loginButton.setBounds(60,200,80,25);
        //登录按钮逻辑
        loginButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = userText.getText();
                String password = passText.getText();
                Person person = new Person(account,password);
                System.out.println(person);
                R r = null;
                try {
                    r = userController.login(person);
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
                //登录成功
                if(r.getCode() == 1){
                    //TODO:进入新界面
                    System.out.println("登录成功");
                    if(person.account .equals("root")){
                        //TODO:root用户，进入地图修改界面
                    }else{
                        //TODO:非root用户，进入地图查看界面
                    }
                //登陆失败
                }else{
                    if(r.getMsg().equals("密码错误")){
                        //TODO：创建新界面，告知密码错误
                        System.out.println("密码错误");
                    }else{
                        //TODO:创建新界面，告知用户名不存在
                        System.out.println("用户名不存在");
                    }
                }
            }
        });
        panel.add(loginButton);
        registerButton.setBounds(220,200,80,25);
        //注册按钮逻辑
        registerButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person person = new Person(userText.getText(), passText.getText());
                try {
                    R r= userController.register(person);
                    if(r.getMsg().equals("禁止注册超级管理员")){
                        //TODO:禁止注册超级管理员
                        System.out.println("禁止注册超级管理员");
                    }else if(r.getMsg().equals("该用户已经被注册")){
                        //TODO:该用户已经被注册
                        System.out.println("该用户已经被注册");
                    }else{
                        //TODO：注册成功
                        System.out.println("注册成功");
                    }
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        panel.add(registerButton);

        //将备注信息填入左下角
        jTextArea.setBounds(60,300,200,100);
        panel.add(jTextArea);


    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Login();
    }
}
