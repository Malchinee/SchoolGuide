package main.ui;

import main.common.MyButton;
import main.common.R;
import main.controller.BuildController;
import main.entity.Building;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map {
    private JFrame jFrame = new JFrame("校园地图");
    private JPanel jPanel = new JPanel();
    private JLabel image = new JLabel(new ImageIcon("./resources/graph.jpg"));
    //查询景点信息
    private int num = -1;
    private JLabel id= new JLabel("编号:");
    private JLabel name = new JLabel("景点名字:");
    private JLabel message = new JLabel("景点描述:");
    private JTextField idText = new JTextField();
    private JTextField nameText = new JTextField();
    private JTextArea messageText = new JTextArea();
    //查询两景点之间的最短路径和所有路径

    //备注栏
    private JTextArea jTextArea = new JTextArea("景点名称-编号:\r\n" +
            "1公寓-1\r\n" +
            "2公寓-2\r\n" +
            "3公寓-3\r\n" +
            "4公寓-4\r\n" +
            "5公寓-5\r\n" +
            "6公寓-6\r\n" +
            "7公寓-7\r\n" +
            "8公寓-8\r\n" +
            "9公寓-9\r\n" +
            "10公寓-10\r\n" +
            "11公寓-11\r\n" +
            "12公寓-12\r\n" +
            "留学生公寓-13\r\n" +
            "A楼-14\r\n" +
            "B楼-15\r\n" +
            "C楼-16\r\n" +
            "E楼-17\r\n" +
            "F楼-18\r\n" +
            "H楼-19\r\n" +
            "M楼-20\r\n" +
            "G楼-21\r\n" +
            "N楼-22\r\n" +
            "学苑餐厅-23\r\n" +
            "学子餐厅-24\r\n" +
            "图书馆-25\r\n" +
            "大学生活动中心-26\r\n" +
            "综合球类馆-27\r\n" +
            "大学生服务区-28\r\n" +
            "体育场-29\r\n" +
            "主楼广场-30\r\n" +
            "研究院-31\r\n" +
            "菜鸟驿站-32");
    private BuildController buildController = new BuildController();
    public Map(){
        //设置窗体的位置及大小
            jFrame.setSize(2000,1000);
        jFrame.setLocationRelativeTo(null);//在屏幕中居中显示
        jFrame.add(jPanel);
        jPanel.setLayout(null);
        placeComponents();
        jFrame.setVisible(true);
    }
    private void placeComponents(){
        //左侧的地图
        image.setBounds(0,0,750,1000);
        jPanel.add(image);
        //备注栏
        jTextArea.setEditable(false);
        jTextArea.setBounds(750,0,225,1000);
        jTextArea.setFont(new Font("宋体",Font.BOLD,24));
        jPanel.add(jTextArea);
        //设置隐形按钮
        setButton();
        //设置查询景点信息
        setBuild();
        //查询最短路
        queryMin();
    }

    private void queryMin(){
        JLabel vex1 = new JLabel("请输入要查询的第一个景点的编号：");
        JLabel vex2 = new JLabel("请输入要查询的第二个景点的编号：");
        JTextField vex1Text = new JTextField();
        JTextField vex2Text = new JTextField();
        JButton minButton = new JButton("查询两点最短路径");
        JButton allButton = new JButton("查询两点所有路径");
        JTextArea jTextArea = new JTextArea();
        JLabel vexAll = new JLabel("请输入要查询的所有景点的编号：（空格隔开）");
        JLabel add = new JLabel("第一个编号为起点，第二个编号为终点");
        JTextField jTextField = new JTextField();
        JButton button = new JButton("查询多个景点之间的路径");
        JLabel path = new JLabel("路径查询信息如下：");

        vex1.setBounds(1000,400,350,40);
        vex1.setFont(new Font("宋体",Font.BOLD,20));
        vex2.setBounds(1000,450,350,40);
        vex2.setFont(new Font("宋体",Font.BOLD,20));
        vex1Text.setBounds(1360,400,40,40);
        vex2Text.setBounds(1360,450,40,40);
        vex1Text.setFont(new Font("宋体",Font.BOLD,20));
        vex2Text.setFont(new Font("宋体",Font.BOLD,20));

        minButton.setBounds(1000,500,400,50);
        allButton.setBounds(1000,570,400,50);
        minButton.setFont(new Font("宋体",Font.BOLD,20));
        allButton.setFont(new Font("宋体",Font.BOLD,20));

        minButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str1 = vex1Text.getText();
                String str2 = vex2Text.getText();
                try {
                    R r = buildController.queryMinPath(str1,str2);
                    if(r.getCode() == 0){
                        JOptionPane.showMessageDialog(null,r.getMsg(),"Something Wrong",JOptionPane.ERROR_MESSAGE);
                    }else{
                        jTextArea.setText(r.getMsg());
                    }
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        allButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str1 = vex1Text.getText();
                String str2 = vex2Text.getText();
                try {
                    R r = buildController.queryAllPath(str1,str2);
                    if(r.getCode() == 0) {
                        JOptionPane.showMessageDialog(null,r.getMsg(),"Something Wrong",JOptionPane.ERROR_MESSAGE);
                    }else{
                        jTextArea.setText(r.getMsg());
                    }
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        jTextArea.setEditable(false);
        jTextArea.setWrapStyleWord(true);
        jTextArea.setLineWrap(true);
        jTextArea.setBounds(1450,45,450,900);
        jTextArea.setFont(new Font("宋体",Font.BOLD,20));
        JScrollPane scrollPane = new JScrollPane(jTextArea);
        scrollPane.setBounds(1900,400,30,500);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



        vexAll.setBounds(1000,700,450,40);
        vexAll.setFont(new Font("宋体",Font.BOLD,20));
        add.setBounds(1000,750,700,40);
        add.setFont(new Font("宋体",Font.BOLD,20));

        jTextField.setBounds(1000,800,400,40);
        jTextField.setFont(new Font("宋体",Font.BOLD,20));
        button.setBounds(1000,850,400,50);
        button.setFont(new Font("宋体",Font.BOLD,20));
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = jTextField.getText();
                try {
                    buildController.queryMore(str);
                    R r = buildController.queryMore(str);
                    if(r.getCode() == 1){
                        jTextArea.setText(r.getMsg());
                    }else{
                        JOptionPane.showMessageDialog(null,r.getMsg(),"Something Wrong",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        path.setBounds(1450,5,450,30);
        path.setFont(new Font("宋体",Font.BOLD,25));

        jPanel.add(vex1);
        jPanel.add(vex2);
        jPanel.add(vex1Text);
        jPanel.add(vex2Text);
        jPanel.add(minButton);
        jPanel.add(allButton);
        jPanel.add(jTextArea);
        jPanel.add(vexAll);
        jPanel.add(button);
        jPanel.add(jTextField);
        jPanel.add(scrollPane);
        jPanel.add(path);
        jPanel.add(add);

    }
    private void setBuild(){
        id.setBounds(1000,5,100,30);
        id.setFont(new Font("宋体",Font.BOLD,20));
        name.setBounds(1000,45,100,20);
        name.setFont(new Font("宋体",Font.BOLD,20));
        message.setBounds(1000,80,100,30);
        message.setFont(new Font("宋体",Font.BOLD,20));
        idText.setBounds(1100,0,100,30);
        idText.setFont(new Font("宋体",Font.BOLD,20));
        idText.setEditable(false);
        nameText.setBounds(1100,40,200,30);
        nameText.setFont(new Font("宋体",Font.BOLD,18));
        nameText.setEditable(false);
        messageText.setBounds(1100,80,200,200);
        messageText.setFont(new Font("宋体",Font.BOLD,20));
        messageText.setEditable(false);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        jPanel.add(id);
        jPanel.add(name);
        jPanel.add(message);
        jPanel.add(idText);
        jPanel.add(nameText);
        jPanel.add(messageText);
    }
    private void setButton(){
        //隐形按钮
        List<JButton> list = new ArrayList<>();
        list.add(new MyButton(305,420,45,35));
        list.add(new MyButton(240,450,50,35));
        list.add(new MyButton(240,400,50,35));
        list.add(new MyButton(240,350,50,35));
        list.add(new MyButton(330,475,50,35));
        list.add(new MyButton(250,290,70,35));
        list.add(new MyButton(130,450,70,35));
        list.add(new MyButton(160,300,50,35));
        list.add(new MyButton(160,240,50,35));
        list.add(new MyButton(90,240,50,35));
        list.add(new MyButton(25,240,50,35));
        list.add(new MyButton(510,230,50,35));
        list.add(new MyButton(510,120,50,70));
        list.add(new MyButton(525,475,50,35));
        list.add(new MyButton(330,525,50,35));
        list.add(new MyButton(625,640,50,35));
        list.add(new MyButton(625,690,50,35));
        list.add(new MyButton(690,660,40,35));
        list.add(new MyButton(420,540,160,50));
        list.add(new MyButton(230,570,70,35));
        list.add(new MyButton(315,570,70,35));
        list.add(new MyButton(90,90,90,35));
        list.add(new MyButton(310,360,60,35));
        list.add(new MyButton(240,240,45,40));
        list.add(new MyButton(250,20,70,40));
        list.add(new MyButton(240,90,70,35));
        list.add(new MyButton(325,90,70,35));
        list.add(new MyButton(240,510,50,35));
        list.add(new MyButton(150,630,250,150));
        list.add(new MyButton(410,630,175,175));
        list.add(new MyButton(600,800,125,100));
        list.add(new MyButton(325,240,35,35));
        for(int i = 1;i <= 32 ;i++){
            int finalI = i;
            list.get(i-1).addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    num = finalI;
                    try {
                        Building build = buildController.queryBuild(num);
                        idText.setText(String.valueOf(build.getNo()));
                        nameText.setText(build.getBuildName());
                        messageText.setText(build.getMessage());
                    } catch (IOException | ClassNotFoundException ioException) {
                        ioException.printStackTrace();
                    }

                }
            });
            list.get(i-1).setContentAreaFilled(false);
            jPanel.add(list.get(i-1));
        }
    }

}
