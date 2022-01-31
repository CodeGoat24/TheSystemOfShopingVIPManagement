package component.Commodity;

import dao.CommodityDao.CommodityDao;
import domain.CommodityBean;
import listener.ActionDoneListener;
import utils.ScreenUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCommodity extends JDialog {
    final int WIDTH = 400;
    final int HEIGHT = 400;
    private String id;
    private ActionDoneListener listener;
    private JTextField idField;
    private JTextField nameField;
    private JTextField birthPlaceField;
    private JTextField birthDayField;
    private JTextField priceField;
    private JTextField stockField;
    private JTextField introductionField;
    private JTextField psField;
    public UpdateCommodity(JFrame jf, String title, boolean isModel, ActionDoneListener listener, String id){
        super(jf,title,isModel);
        this.listener  = listener;
        this.id=id;
        CommodityDao dao = new CommodityDao();
        CommodityBean com = dao.findById(Integer.parseInt(id));
//        组装试图
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);

        Box vBox = Box.createVerticalBox();

        //组装商品编号
        Box idBox = Box.createHorizontalBox();
        JLabel idLable = new JLabel("商品编号：");
        idField = new JTextField(15);
        idField.setText("" + com.getId());
        idField.setEnabled(false);

        idBox.add(idLable);
        idBox.add(Box.createHorizontalStrut(20));
        idBox.add(idField);

        //组装商品名称
        Box nameBox = Box.createHorizontalBox();
        JLabel nameLable = new JLabel("商品名称：");
        nameField = new JTextField(15);
        nameField.setText(com.getName());

        nameBox.add(nameLable);
        nameBox.add(Box.createHorizontalStrut(20));
        nameBox.add(nameField);

        //组装商品生产地
        Box birthPlaceBox = Box.createHorizontalBox();
        JLabel birthPlaceLable = new JLabel("    生产地：");
        birthPlaceField = new JTextField(15);
        birthPlaceField.setText(com.getBirthPlace());

        birthPlaceBox.add(birthPlaceLable);
        birthPlaceBox.add(Box.createHorizontalStrut(20));
        birthPlaceBox.add(birthPlaceField);

        //组装商品生产日期
        Box birthDayBox = Box.createHorizontalBox();
        JLabel birthDayLable = new JLabel("生产日期：");
        birthDayField = new JTextField(15);
        birthDayField.setText(com.getBirthDay());

        birthDayBox.add(birthDayLable);
        birthDayBox.add(Box.createHorizontalStrut(20));
        birthDayBox.add(birthDayField);

        //组装商品价格
        Box priceBox = Box.createHorizontalBox();
        JLabel priceLable = new JLabel("        价格：");
        priceField = new JTextField(15);
        priceField.setText("" + com.getPrice());

        priceBox.add(priceLable);
        priceBox.add(Box.createHorizontalStrut(20));
        priceBox.add(priceField);

        //组装商品库存
        Box stockBox = Box.createHorizontalBox();
        JLabel stockLable = new JLabel("商品库存：");
        stockField = new JTextField(15);
        stockField.setText("" + com.getStock());

        stockBox.add(stockLable);
        stockBox.add(Box.createHorizontalStrut(20));
        stockBox.add(stockField);

        //组装商品简介
        Box introductionBox = Box.createHorizontalBox();
        JLabel introdutionLable = new JLabel("商品简介：");
        introductionField = new JTextField(15);
        introductionField.setText(com.getIntroduction());

        introductionBox.add(introdutionLable);
        introductionBox.add(Box.createHorizontalStrut(20));
        introductionBox.add(introductionField);

        //组装商品备注
        Box psBox = Box.createHorizontalBox();
        JLabel psLable = new JLabel("商品备注：");
        psField = new JTextField(15);
        psField.setText(com.getPs());

        psBox.add(psLable);
        psBox.add(Box.createHorizontalStrut(20));
        psBox.add(psField);

        Box btnBox = Box.createHorizontalBox();
        JButton updateBtn = new JButton("修改");


        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户修改后在输入框中输入的内容
                String name = nameField.getText().trim();
                if(name == null || name.equals("")){
                    JOptionPane.showMessageDialog(jf, "商品名称不得为空！");
                    return;
                }
                CommodityDao dao = new CommodityDao();
                CommodityBean cb = dao.findByNameForPrice(name);
                if(cb!=null && cb.getId() != Integer.parseInt(id)){
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
                dao.upadate(com);

                JOptionPane.showMessageDialog(jf,"修改成功");
                dispose();
                listener.done(null);
            }
        });

        btnBox.add(updateBtn);

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
