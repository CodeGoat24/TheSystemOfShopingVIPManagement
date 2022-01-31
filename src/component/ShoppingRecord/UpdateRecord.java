package component.ShoppingRecord;

import dao.CommodityDao.CommodityDao;
import dao.CommodityDao.ShoppingRecordDao;
import domain.CommodityBean;
import domain.ShoppingRecordBean;
import listener.ActionDoneListener;
import utils.ScreenUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateRecord extends JDialog {
    final int WIDTH = 400;
    final int HEIGHT = 400;
    private String id;
    private ActionDoneListener listener;
    private JTextField idField;
    private JTextField vipnameField;
    private JTextField phoneField;
    private JTextField comnameField;
    private JTextField priceField;
    private JTextField amountField;
    private JTextField totalMoneyField;
    private JTextField dateField;
    public UpdateRecord(JFrame jf, String title, boolean isModel, ActionDoneListener listener, String id){
        super(jf,title,isModel);
        this.listener  = listener;
        this.id=id;
        ShoppingRecordDao dao = new ShoppingRecordDao();
        ShoppingRecordBean rd = dao.findById(id);
//        组装试图
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);

        Box vBox = Box.createVerticalBox();

        //组装订单编号
        Box idBox = Box.createHorizontalBox();
        JLabel idLable = new JLabel("订单编号：");
        idField = new JTextField(15);
        idField.setText("" + id);
        idField.setEnabled(false);

        idBox.add(idLable);
        idBox.add(Box.createHorizontalStrut(20));
        idBox.add(idField);

        //组装会员名称
        Box vipnameBox = Box.createHorizontalBox();
        JLabel vipnameLable = new JLabel("会员名称：");
        vipnameField = new JTextField(15);
        vipnameField.setText(rd.getVipname());
        vipnameField.setEnabled(false);

        vipnameBox.add(vipnameLable);
        vipnameBox.add(Box.createHorizontalStrut(20));
        vipnameBox.add(vipnameField);

        //组装手机号
        Box phoneBox = Box.createHorizontalBox();
        JLabel phoneLable = new JLabel("    手机号：");
        phoneField = new JTextField(15);
        phoneField.setText(rd.getPhone());
        phoneField.setEnabled(false);

        phoneBox.add(phoneLable);
        phoneBox.add(Box.createHorizontalStrut(20));
        phoneBox.add(phoneField);

        //组装商品名称
        Box comnameBox = Box.createHorizontalBox();
        JLabel comnameLable = new JLabel("商品名称：");
        comnameField = new JTextField(15);
        comnameField.setText(rd.getComname());

        comnameBox.add(comnameLable);
        comnameBox.add(Box.createHorizontalStrut(20));
        comnameBox.add(comnameField);

        //组装商品单价
        Box priceBox = Box.createHorizontalBox();
        JLabel priceLable = new JLabel("        单价：");
        priceField = new JTextField(15);
        priceField.setText("" + rd.getPrice());
        priceField.setEnabled(false);
        priceBox.add(priceLable);
        priceBox.add(Box.createHorizontalStrut(20));
        priceBox.add(priceField);

        //组装商品数量
        Box amountBox = Box.createHorizontalBox();
        JLabel amountLable = new JLabel("购买数量：");
        amountField = new JTextField(15);
        amountField.setText("" + rd.getAmount());

        amountBox.add(amountLable);
        amountBox.add(Box.createHorizontalStrut(20));
        amountBox.add(amountField);

        //组装消费合计
        Box totalMoneyBox = Box.createHorizontalBox();
        JLabel totalMoneyLable = new JLabel("消费合计：");
        totalMoneyField = new JTextField(15);
        totalMoneyField.setText(""+rd.getTotalMoney());
        totalMoneyField.setEnabled(false);

        totalMoneyBox.add(totalMoneyLable);
        totalMoneyBox.add(Box.createHorizontalStrut(20));
        totalMoneyBox.add(totalMoneyField);

        //组装订单日期
        Box dateBox = Box.createHorizontalBox();
        JLabel dateLable = new JLabel("订单日期：");
        dateField = new JTextField(15);
        dateField.setText(rd.getDate());

        dateBox.add(dateLable);
        dateBox.add(Box.createHorizontalStrut(20));
        dateBox.add(dateField);

        Box btnBox = Box.createHorizontalBox();
        JButton updateBtn = new JButton("修改");

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户修改后在输入框中输入的内容
                String id = idField.getText().trim();
                String vipname = vipnameField.getText().trim();
                String phone = phoneField.getText().trim();
                String comname = comnameField.getText().trim();

                CommodityDao cdao = new CommodityDao();
                CommodityBean com = cdao.findByNameForPrice(comname);
                if(comname == null || comname.equals("")){
                    JOptionPane.showMessageDialog(jf, "商品名称不得为空！");
                    return;
                }
                if(com == null){
                    JOptionPane.showMessageDialog(jf, "不存在此商品！");
                    return;
                }
                String price = String.valueOf(com.getPrice());
                String amount = amountField.getText().trim();
                if(amount == null || amount.equals("") || Double.parseDouble(amount) <= 0){
                    JOptionPane.showMessageDialog(jf, "购买数量不合要求！");
                    return;
                }
//                if(Double.parseDouble(amount) > (double) com.getStock()){
//                    JOptionPane.showMessageDialog(jf, "该商品库存仅剩下："+ com.getStock());
//                    return;
//                }
                String totalMoney = "" + String.format("%.2f", Double.parseDouble(price) * Double.parseDouble(amount));
                String date = dateField.getText().trim();
                ShoppingRecordBean rd = new ShoppingRecordBean();
                rd.setId(id);
                rd.setVipname(vipname);
                rd.setPhone(phone);
                rd.setComname(comname);
                rd.setPrice(Double.parseDouble(price));
                rd.setAmount(Integer.parseInt(amount));
                rd.setTotalMoney(Double.parseDouble(totalMoney));
                rd.setDate(date);
                dao.upadate(rd);
                com.setStock(com.getStock() - rd.getAmount());
                cdao.upadate(com);
                JOptionPane.showMessageDialog(jf,"修改成功");
                dispose();
                listener.done(null);
            }
        });

        btnBox.add(updateBtn);

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
