package GUI;

import component.BackGroundPanel;
import utils.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainInterface {

    JFrame jf = new JFrame("商场VIP消费管理系统");

    final int WIDTH = 500;
    final int HEIGHT = 300;

    public void init() throws IOException {
        //窗口大小和位置设置
        jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH)/2, (ScreenUtils.getScreenHeight() - HEIGHT)/2, WIDTH, HEIGHT);
        jf.setResizable(false);

//        设置窗口图标
        jf.setIconImage(ImageIO.read(new File("src/images/loginIcon.png")));

        //自定义的背景容器创建
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("src/images/loginBg.jpg")));

        //垂直盒子创建
        Box vBox = Box.createVerticalBox();

        //组装用户名行
        Box uBox = Box.createHorizontalBox();
        JLabel uLable = new JLabel("用户名：");
        JTextField uFiled = new JTextField(15);

        uBox.add(uLable);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uFiled);

        //组装密码行
        Box pBox = Box.createHorizontalBox();
        JLabel pLable = new JLabel("密    码：");
        JPasswordField pFiled = new JPasswordField(15);

        pBox.add(pLable);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pFiled);


        //组装按钮行
        Box btnBox = Box.createHorizontalBox();
        JButton loginButton = new JButton("登录");
        btnBox.add(loginButton);

        //给登录按钮创建监听
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户输入的数据
                String username = uFiled.getText().trim();
                String password = new String(pFiled.getPassword());
                if(username.equals("admin") && password.equals("123")){
                    new ManagerInterface().init();
                    jf.dispose();
                }else{
                    JOptionPane.showMessageDialog(jf, "用户名或密码错误");

                }

            }
        });

        //在垂直容器中加入行
        vBox.add(Box.createVerticalStrut(50));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(btnBox);
        //在背景容器中加入vBox
        bgPanel.add(vBox);
        jf.add(bgPanel);
//        jf.pack();
        jf.setVisible(true);
    }


    public static void main(String[] args) throws IOException {
        new MainInterface().init();
    }
}
