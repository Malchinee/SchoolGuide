package main.ui;

import main.common.R;
import main.controller.BuildController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class BuildManager {
    private JFrame jFrame = new JFrame("景点管理界面");
    private JPanel jPanel = new JPanel();
    private JTextArea jTextArea = new JTextArea("景点名称-编号:\r\n" +
            "        1公寓-1\r\n" +
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

    private BuildController buildController = new BuildController();
    private int num = -1;//默认不查询
    private JButton cancel = new JButton("不改了");
    public BuildManager (){
        //设置窗体的位置及大小
        jFrame.setSize(600,600);
        jFrame.setLocationRelativeTo(null);//在屏幕中居中显示
        jFrame.add(jPanel);//添加面板
        placeComponents(jPanel);
        jFrame.setVisible(true);
    }

    private void placeComponents(JPanel jPanel){

        jPanel.setLayout(null);
        //备注栏
        jTextArea.setBounds(10,10,150,650);
        jTextArea.setEditable(false);
        jPanel.add(jTextArea);
        //“请输入景点编号”
        jLabel.setBounds(170,10,400,50);
        jLabel.setFont(new Font("宋体",Font.BOLD,20));
        jPanel.add(jLabel);
        //景点代号输入框
        jTextField.setBounds(170,80,50,50);
        jTextField.setFont(new Font("宋体",Font.BOLD,20));
        jPanel.add(jTextField);
        jButton.setBounds(250,90,100,40);
        jButton.setFont(new Font("宋体",Font.BOLD,20));
        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取景点编号输入框的内容，并将其转化为int
                String str = jTextField.getText();
                for(int i = 0;i < str.length();i++){
                    if(str.charAt(i) < '0' || str.charAt(i) >'9'){
                        JOptionPane.showMessageDialog(null,"编号格式错误","Something Wrong",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                num = Integer.parseInt(jTextField.getText());
                String msg = null;
                try {
                    msg = buildController.getBuildingMessage(num).getMsg();
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
                if(msg.equals("请输入正确的编号")){
                    JOptionPane.showMessageDialog(null,msg,"Something Wrong",JOptionPane.ERROR_MESSAGE);
                }else{
                    //将查询到的信息输入到建筑物消息框中
                    buildMes.setText(msg);
                }
                jTextField.setEditable(false);
            }
        });
        jPanel.add(jButton);
        cancel.setBounds(360,90,100,40);
        cancel.setFont(new Font("宋体",Font.BOLD,20));
        cancel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField.setEditable(true);
                buildMes.setText("");
            }
        });
        jPanel.add(cancel);
        //景点信息
        buildMes.setBounds(170,150,350,150);
        buildMes.setFont(new Font("宋体",Font.BOLD,20));
        jPanel.add(buildMes);
        //确定修改按钮
        sure.setBounds(220,320,150,40);
        sure.setFont(new Font("宋体",Font.BOLD,20));
        sure.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newMsg = buildMes.getText();
                int newNum = num;
                R r = null;
                try {
                    r = buildController.updateBuildMsg(newNum,newMsg);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                if(r.getCode() == 0) {
                    JOptionPane.showMessageDialog(null, r.getMsg(), "Something Wrong", JOptionPane.ERROR_MESSAGE);
                }else{
                    try {
                        buildMes.setText(buildController.getBuildingMessage(newNum).getMsg());
                    } catch (IOException | ClassNotFoundException ioException) {
                        ioException.printStackTrace();
                    }
                }

            }
        });
        jPanel.add(sure);
    }


}
