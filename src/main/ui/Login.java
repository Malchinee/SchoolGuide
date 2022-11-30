package main.ui;

import main.common.R;
import main.controller.UserController;
import main.dao.BuildDao;
import main.entity.Person;

import javax.swing.*;
import java.awt.*;
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
    public Login()  {

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
        userLabel.setBounds(60,60,200,25);
        userLabel.setFont(new Font("宋体",Font.BOLD,20));
        panel.add(userLabel);
        //创建文本域供用户输入
        userText.setBounds(250,60,165,25);
        userText.setFont(new Font("宋体",Font.BOLD,24));
        panel.add(userText);

        //创建passJLabel
        passLabel.setBounds(60,100,200,25);
        passLabel.setFont(new Font("宋体",Font.BOLD,20));
        panel.add(passLabel);
        //创建密码输入框
        passText.setBounds(250,100,165,25);
        passText.setFont(new Font("宋体",Font.BOLD,24));
        panel.add(passText);
        //创建登陆注册按钮
        loginButton.setBounds(60,200,150,30);
        loginButton.setFont(new Font("宋体",Font.BOLD,24));
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
                    System.out.println("登录成功");
                    if(person.account .equals("root")){
                        //root用户，进入管理界面
                        new BuildManager();
                    }else{
                        //非root用户，进入地图界面
                        new Map();
                    }
                //登陆失败
                }else{
                    JOptionPane.showMessageDialog(null,r.getMsg(),"Something Wrong",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(loginButton);
        registerButton.setBounds(220,200,150,30);
        registerButton.setFont(new Font("宋体",Font.BOLD,20));
        //注册按钮逻辑
        registerButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person person = new Person(userText.getText(), passText.getText());
                try {
                    R r= userController.register(person);
                    if(r.getCode() == 1){
                        JOptionPane.showMessageDialog(null,"注册成功","Success",1);
                    }else{
                        JOptionPane.showMessageDialog(null,r.getMsg(),"Something Wrong",0);
                    }
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        panel.add(registerButton);

        //将备注信息填入左下角
        jTextArea.setBounds(60,300,400,75);
        jTextArea.setFont(new Font("宋体",Font.BOLD,24));
        panel.add(jTextArea);


    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Login();
    }
}
