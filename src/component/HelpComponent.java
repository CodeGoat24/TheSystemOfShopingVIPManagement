package component;

import utils.ScreenUtils;

import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.*;

public class HelpComponent extends JDialog {
    final int WIDTH = 606;
    final int HEIGHT = 600;
    public HelpComponent(JFrame jf, String title, boolean isModel){
        super(jf, title, isModel);
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        this.setResizable(false);
        this.setLayout(null);
        String context = "商场消费管理系统使用指南：\n" +
                "          一、 商品信息管理：\n" +
                "\t1、添加：添加商品时，商品编号（在数据库中查询商品信息的标识）和商品名称（在添加消费记录中要通过填写的商品名称在数据库中找到对应商品信息）不能与数据库中已有商品信息重复。\n" +
                "\t2、修改：修改商品信息时，商品编号不得修改，商品名称同样不能与其他商品重复\n" +
                "\t3、删除：删除将从数据库中直接删除此商品信息，页面重新打开，表格内容刷新。\n" +
                "\t4、查询：输入商品名称关键字，将模糊查询出所有包含此关键字的商品信息，将在新页面展示，可以对查询到的信息进行修改和删除。在查询页面执行修改和删除信息后，主页面和查询页面表格内容将立刻刷新\n" +
                "\n" +
                "           二、VIP信息管理：\n" +
                "\t1、添加：添加VIP时，会员手机号（在数据库中查询会员信息的标识）不能与数据库中已有商品信息重复。\n" +
                "\t2、修改：修改会员信息时，会员手机号不得修改\n" +
                "\t3、删除：删除将从数据库中直接删除此会员信息，页面重新打开，表格内容刷新。\n" +
                "\t4、查询：输入会员名称关键字，将模糊查询出所有名称包含此关键字的会员信息，将在新页面展示，可以对查询到的信息进行修改和删除。在查询页面执行修改和删除信息后，主页面和查询页面表格内容将立刻刷新\n" +
                "\n" +
                "           三、VIP消费订单管理\n" +
                "\t1、添加：添加订单时，只需要输入手机号、商品名称、购买数量，系统会根据手机号查询到会员信息，获取会员名称，根据商品名称查询到对应商品信息，获得商品单价。若数据库中\n" +
                "\t\t（1）没有绑定此手机号的会员\n" +
                "\t\t（2）或没有名称为输入字符串的商品\n" +
                "\t\t（3）购买数量大于数据库中该商品库存数量\n" +
                "\t则会弹出相应的提示框，添加失败。订单添加成功后，数据库中订单中被卖出商品的库存数量将减去被购买数量，订单消费合计将由系统自动计算出。\n" +
                "\t2、修改：修改订单信息时，订单编号不允许修改，其他修改条件判断与上述添加条件类似\n" +
                "\t3、删除：删除将从数据库中直接删除此订单信息，页面重新打开，表格内容刷新。\n" +
                "\t4、查询：输入会员名称关键字，将模糊查询出所有名称包含此关键字的会员的相关的消费信息，将在新页面展示，可以对查询到的信息进行修改和删除。在查询页面执行修改和删除信息后，主页面和查询页面表格内容将立刻刷新";
        JTextArea text = new JTextArea();
        text.setText(context);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setText(context);
        text.setBounds(0,0,WIDTH,HEIGHT);
        this.add(text);
    }
}
