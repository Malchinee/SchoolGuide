package main.ui;

import main.controller.BuildController;

import javax.swing.*;

public class BuildManager {
    private JFrame jFrame = new JFrame("景点管理界面");
    private JPanel jPanel = new JPanel();
    private JTextArea jTextArea = new JTextArea("景点名称-编号:\r\n1公寓-1\r\n" +
            "        2公寓-2\r\n" +
            "        3公寓-3\r\n" +
            "        4公寓-4\r\n" +
            "        5公寓-5\r\n" +
            "        6公寓-6\r\n" +
            "        7公寓-7\r\n" +
            "        8公寓-8\r\n" +
            "        9公寓-9\r\n" +
            "        10公寓-10\r\n" +
            "        11公寓-11\r\n" +
            "        12公寓-12\r\n" +
            "        留学生公寓-13\r\n" +
            "        A楼-14\r\n" +
            "        B楼-15\r\n" +
            "        C楼-16\r\n" +
            "        E楼-17\r\n" +
            "        F楼-18\r\n" +
            "        H楼-19\r\n" +
            "        M楼-20\r\n" +
            "        G楼-21\r\n" +
            "        N楼-22\r\n" +
            "        学苑餐厅-23\r\n" +
            "        学子餐厅-24\r\n" +
            "        图书馆-25\r\n" +
            "        大学生活动中心-26\r\n" +
            "        综合球类馆-27\r\n" +
            "        大学生服务区-28\r\n" +
            "        体育场-29\r\n" +
            "        主楼广场-30\r\n" +
            "        研究院-31\r\n" +
            "        菜鸟驿站-32");
    private JTextArea buildMes = new JTextArea();
    private JLabel jLabel = new JLabel("请输入要修改的景点的编号：");
    private JTextField jTextField = new JTextField();
    private JButton jButton = new JButton("确定");

    private JButton sure = new JButton("确定修改");

    public BuildManager (){
        //设置窗体的位置及大小
        jFrame.setSize(450,600);
        jFrame.setLocationRelativeTo(null);//在屏幕中居中显示
        jFrame.add(jPanel);//添加面板
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击X号关闭
        placeComponents(jPanel);
        jFrame.setVisible(true);
    }

    private void placeComponents(JPanel jPanel){
        jPanel.setLayout(null);
        jTextArea.setBounds(10,10,150,650);
        jPanel.add(jTextArea);
        buildMes.setBounds(200,10,250,200);
        jPanel.add(buildMes);
        jButton.setBounds(250,220,100,50);
        jPanel.add(jButton);
    }

    public static void main(String[] args) {
        new BuildManager();
    }

}
