package GUI;

import component.BackGroundPanel;
import component.Commodity.CommodityManageComponent;
import component.HelpComponent;
import component.ShoppingRecord.ShoppingRecordComponent;
import component.VIPimformation.VIPimformationComponent;
import utils.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ManagerInterface  {
    //创建窗口
    JFrame jf = new JFrame("商场VIP消费管理系统：管理员，欢迎您");

    final int WIDTH = 1000;
    final int HEIGHT = 600;


    public void init() {


        //给窗口设置属性
        jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
        jf.setResizable(false);
        try {
            jf.setIconImage(ImageIO.read(new File("src/images/ManagerInterface.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }


//        设置菜单栏
        JMenuBar jmb = new JMenuBar();
        JMenu jMenu = new JMenu("设置");
        JMenuItem m1 = new JMenuItem("注销账户");
        JMenuItem m2 = new JMenuItem("退出程序");
        JMenuItem help = new JMenuItem("帮助");


        m1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new MainInterface().init();
                    jf.dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        m2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HelpComponent(jf, "帮助", true).setVisible(true);
            }
        });

        jMenu.add(m1);
        jMenu.add(help);
        jMenu.add(m2);
        jmb.add(jMenu);
        jf.setJMenuBar(jmb);

//        设置分割面板
        JSplitPane sp = new JSplitPane();

        //支持连续布局
        sp.setContinuousLayout(true);
//        sp.setEnabled(false);
        sp.setDividerLocation(195);
        sp.setDividerSize(7);


        //设置左侧内容
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("系统管理");
        DefaultMutableTreeNode commodityImfoManage = new DefaultMutableTreeNode("商品信息管理");
        DefaultMutableTreeNode vipManage = new DefaultMutableTreeNode("VIP管理");
        DefaultMutableTreeNode vipImfoManage = new DefaultMutableTreeNode("VIP信息管理");
        DefaultMutableTreeNode shoppingImfoManage = new DefaultMutableTreeNode("VIP消费订单管理");

        root.add(commodityImfoManage);

        vipManage.add(vipImfoManage);
        vipManage.add(shoppingImfoManage);
        root.add(vipManage);
        JTree tree = new JTree(root);

        Color color = new Color(203, 220, 217);
        tree.setBackground(color);
        //默认选择商品管理
        tree.setSelectionRow(1);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();
                if (commodityImfoManage.equals(lastPathComponent)) {
                    sp.setRightComponent(new CommodityManageComponent(sp));
                } else if (vipImfoManage.equals(lastPathComponent)) {
                    sp.setRightComponent(new VIPimformationComponent(sp));
                } else if (shoppingImfoManage.equals(lastPathComponent)) {
                    sp.setRightComponent(new ShoppingRecordComponent(sp));
                }
            }
        });
        sp.setLeftComponent(tree);
        sp.setRightComponent(new CommodityManageComponent(sp));
        sp.setOneTouchExpandable(true);
        jf.add(sp);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new ManagerInterface().init();
    }
}
