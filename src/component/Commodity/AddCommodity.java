package component.Commodity;

import dao.CommodityDao.CommodityDao;
import domain.CommodityBean;
import listener.ActionDoneListener;
import utils.ScreenUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCommodity extends JDialog {
    final int WIDTH = 400;
    final int HEIGHT = 400;
    private ActionDoneListener listener;
    public AddCommodity(JFrame jf, String title, boolean isModel, ActionDoneListener listener){
        super(jf,title,isModel);
        this.listener = listener;
        //组装视图
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        this.setResizable(false);
        Box vBox = Box.createVerticalBox();

        //组装商品名称
        Box idBox = Box.createHorizontalBox();
        JLabel idLable = new JLabel("商品编号：");
        JTextField idField = new JTextField(15);

        idBox.add(idLable);
        idBox.add(Box.createHorizontalStrut(20));
        idBox.add(idField);

        //组装商品名称
        Box nameBox = Box.createHorizontalBox();
        JLabel nameLable = new JLabel("商品名称：");
        JTextField nameField = new JTextField(15);

        nameBox.add(nameLable);
        nameBox.add(Box.createHorizontalStrut(20));
        nameBox.add(nameField);

        //组装商品生产地
        Box birthPlaceBox = Box.createHorizontalBox();
        JLabel birthPlaceLable = new JLabel("    生产地：");
        JTextField birthPlaceField = new JTextField(15);

        birthPlaceBox.add(birthPlaceLable);
        birthPlaceBox.add(Box.createHorizontalStrut(20));
        birthPlaceBox.add(birthPlaceField);

        //组装商品生产日期
        Box birthDayBox = Box.createHorizontalBox();
        JLabel birthDayLable = new JLabel("生产日期：");
        JTextField birthDayField = new JTextField(15);

        birthDayBox.add(birthDayLable);
        birthDayBox.add(Box.createHorizontalStrut(20));
        birthDayBox.add(birthDayField);

        //组装商品价格
        Box priceBox = Box.createHorizontalBox();
        JLabel priceLable = new JLabel("        价格：");
        JTextField priceField = new JTextField(15);

        priceBox.add(priceLable);
        priceBox.add(Box.createHorizontalStrut(20));
        priceBox.add(priceField);

        //组装商品库存
        Box stockBox = Box.createHorizontalBox();
        JLabel stockLable = new JLabel("商品库存：");
        JTextField stockField = new JTextField(15);

        stockBox.add(stockLable);
        stockBox.add(Box.createHorizontalStrut(20));
        stockBox.add(stockField);

        //组装商品简介
        Box introductionBox = Box.createHorizontalBox();
        JLabel introdutionLable = new JLabel("商品简介：");
        JTextField introductionField = new JTextField(15);

        introductionBox.add(introdutionLable);
        introductionBox.add(Box.createHorizontalStrut(20));
        introductionBox.add(introductionField);

        //组装商品备注
        Box psBox = Box.createHorizontalBox();
        JLabel psLable = new JLabel("商品备注：");
        JTextField psField = new JTextField(15);

        psBox.add(psLable);
        psBox.add(Box.createHorizontalStrut(20));
        psBox.add(psField);

        //组装添加按钮
        Box btnBox = Box.createHorizontalBox();
        JButton addBtn = new JButton("添加");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户的录入
                String id = idField.getText().trim();
                if(id == null || id.equals("")){
                    JOptionPane.showMessageDialog(jf, "商品编号不得为空！");
                    return;
                }
                CommodityDao dao = new CommodityDao();
                CommodityBean cb = dao.findById(Integer.parseInt(id));
                if(cb!=null){
                    JOptionPane.showMessageDialog(jf, "此商品编号已存在，请重新输入！");
                    return;
                }
                String name = nameField.getText().trim();
                if(name == null || name.equals("")){
                    JOptionPane.showMessageDialog(jf, "商品名称不得为空！");
                    return;
                }
                cb = dao.findByNameForPrice(name);
                if(cb!=null){
                    JOptionPane.showMessageDialog(jf, "此商品已存在，请重新输入！");
                    return;
                }
                String birthPlace = birthPlaceField.getText().trim();
                String birthDay = birthDayField.getText().trim();
                String price = priceField.getText().trim();
                if(price == null || price.equals("")){
                    price = "0";
                }
                String stock = stockField.getText().trim();
                if(stock == null || stock.equals("")){
                    stock = "0";
                }
                String introduction = introductionField.getText().trim();
                String ps = psField.getText().trim();
                CommodityBean com = new CommodityBean();
                com.setId(Integer.parseInt(id));
                com.setName(name);
                com.setBirthPlace(birthPlace);
                com.setBirthDay(birthDay);
                com.setPrice(Double.parseDouble(price));
                com.setStock(Integer.parseInt(stock));
                com.setIntroduction(introduction);
                com.setPs(ps);

                dao.add(com);
                JOptionPane.showMessageDialog(jf,"添加成功");
                dispose();
                listener.done(null);
            }
        });

        btnBox.add(addBtn);

        vBox.add(Box.createVerticalStrut(20));
        vBox.add(idBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(nameBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(birthPlaceBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(birthDayBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(priceBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(stockBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(introductionBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(psBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(btnBox);

        //为了左右有间距，在vBox外层封装一个水平的Box，添加间隔
        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(20));
        hBox.add(vBox);
        hBox.add(Box.createHorizontalStrut(20));

        this.add(hBox);

    }
}
