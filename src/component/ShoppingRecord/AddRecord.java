package component.ShoppingRecord;

import dao.CommodityDao.CommodityDao;
import dao.CommodityDao.ShoppingRecordDao;
import dao.CommodityDao.VIPDao;
import domain.CommodityBean;
import domain.ShoppingRecordBean;
import domain.VIPBean;
import listener.ActionDoneListener;
import utils.ScreenUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddRecord extends JDialog {
    final int WIDTH = 400;
    final int HEIGHT = 400;
    private ActionDoneListener listener;
    public AddRecord(JFrame jf, String title, boolean isModel, ActionDoneListener listener){
        super(jf,title,isModel);
        this.listener = listener;
        //组装视图
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        this.setResizable(false);
        Box vBox = Box.createVerticalBox();

        //组装订单编号
        Box idBox = Box.createHorizontalBox();
        JLabel idLable = new JLabel("订单编号：");
        JTextField idField = new JTextField(15);

        idBox.add(idLable);
        idBox.add(Box.createHorizontalStrut(20));
        idBox.add(idField);

        //组装消费会员名称
        Box vipnameBox = Box.createHorizontalBox();
        JLabel vipnameLable = new JLabel("会员名称：");
        JTextField vipnameField = new JTextField(15);
        vipnameField.setEnabled(false);
        vipnameBox.add(vipnameLable);
        vipnameBox.add(Box.createHorizontalStrut(20));
        vipnameBox.add(vipnameField);

        //组装商品生产地
        Box phoneBox = Box.createHorizontalBox();
        JLabel phoneLable = new JLabel("    手机号：");
        JTextField phoneField = new JTextField(15);

        phoneBox.add(phoneLable);
        phoneBox.add(Box.createHorizontalStrut(20));
        phoneBox.add(phoneField);

        //组装商品生产日期
        Box comnameBox = Box.createHorizontalBox();
        JLabel comnameLable = new JLabel("商品名称：");
        JTextField comnameField = new JTextField(15);

        comnameBox.add(comnameLable);
        comnameBox.add(Box.createHorizontalStrut(20));
        comnameBox.add(comnameField);

        //组装商品价格
        Box priceBox = Box.createHorizontalBox();
        JLabel priceLable = new JLabel("        单价：");
        JTextField priceField = new JTextField(15);
        priceField.setEnabled(false);
        priceBox.add(priceLable);
        priceBox.add(Box.createHorizontalStrut(20));
        priceBox.add(priceField);

        //组装商品数量
        Box amountBox = Box.createHorizontalBox();
        JLabel amountLable = new JLabel("购买数量：");
        JTextField amountField = new JTextField(15);

        amountBox.add(amountLable);
        amountBox.add(Box.createHorizontalStrut(20));
        amountBox.add(amountField);

        //组装合计消费
        Box totalMoneyBox = Box.createHorizontalBox();
        JLabel totalMoneyLable = new JLabel("合计消费：");
        JTextField totalMoneyField = new JTextField(15);
        totalMoneyField.setEnabled(false);
        totalMoneyBox.add(totalMoneyLable);
        totalMoneyBox.add(Box.createHorizontalStrut(20));
        totalMoneyBox.add(totalMoneyField);

        //组装消费日期
        Box dateBox = Box.createHorizontalBox();
        JLabel dateLable = new JLabel("消费日期：");
        JTextField dateField = new JTextField(15);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date d = new Date();
        String date = df.format(d.getTime());
        dateField.setText(date);
        dateBox.add(dateLable);
        dateBox.add(Box.createHorizontalStrut(20));
        dateBox.add(dateField);

        //组装添加按钮
        Box btnBox = Box.createHorizontalBox();
        JButton addBtn = new JButton("添加");

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户的录入
                String id = idField.getText().trim();
                if(id == null || id.equals("")){
                    JOptionPane.showMessageDialog(jf, "订单编号不得为空！");
                    return;
                }
                ShoppingRecordDao dao = new ShoppingRecordDao();
                ShoppingRecordBean cb = dao.findById(id);
                if(cb!=null){
                    JOptionPane.showMessageDialog(jf, "此订单编号已存在，请重新输入！");
                    return;
                }

                String phone = phoneField.getText().trim();
                if(phone == null || phone.equals("")){
                    JOptionPane.showMessageDialog(jf, "手机号不得为空！");
                    return;
                }

                VIPDao vdao = new VIPDao();
                VIPBean vip = vdao.findByPhone(phone);
                if(vip == null){
                    JOptionPane.showMessageDialog(jf, "不存在此会员");
                    return;
                }
                String vipname = vip.getName();

                String comname = comnameField.getText().trim();
                if(comname == null || comname.equals("")){
                    JOptionPane.showMessageDialog(jf, "商品名称不得为空！");
                    return;
                }
                CommodityDao cdao = new CommodityDao();
                CommodityBean com = cdao.findByNameForPrice(comname);
                if(com == null){
                    JOptionPane.showMessageDialog(jf, "不存在此商品！");
                    return;
                }
                String price = String.valueOf(com.getPrice());
                String amount = amountField.getText().trim();
                if(amount == null || amount.equals("") || Integer.parseInt(amount) <= 0){
                    JOptionPane.showMessageDialog(jf, "购买数量不合要求！");
                    return;
                }
                if(Integer.parseInt(amount) > com.getStock()){
                    JOptionPane.showMessageDialog(jf, "该商品库存仅剩下："+ com.getStock());
                    return;
                }

                String totalMoney = "" + String.format("%.2f", Double.parseDouble(price) * Double.parseDouble(amount));
                String date = dateField.getText().trim();
                ShoppingRecordBean rd = new ShoppingRecordBean();
                rd.setId(id);
                rd.setVipname(vipname);
                rd.setPhone(phone);
                rd.setComname(comname);
                rd.setAmount(Integer.parseInt(amount));
                rd.setPrice(Double.parseDouble(price));
                rd.setTotalMoney(Double.parseDouble(totalMoney));
                rd.setDate(date);

                dao.add(rd);
                com.setStock(com.getStock() - rd.getAmount());
                cdao.upadate(com);
                JOptionPane.showMessageDialog(jf,"添加成功, 会员" +vipname+ "合计消费" + totalMoney + "元");
                dispose();
                listener.done(null);
            }
        });

        btnBox.add(addBtn);

        vBox.add(Box.createVerticalStrut(20));
        vBox.add(idBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(vipnameBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(phoneBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(comnameBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(priceBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(amountBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(totalMoneyBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(dateBox);
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
