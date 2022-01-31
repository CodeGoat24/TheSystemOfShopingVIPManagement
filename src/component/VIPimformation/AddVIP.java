package component.VIPimformation;

import dao.CommodityDao.CommodityDao;
import dao.CommodityDao.VIPDao;
import domain.CommodityBean;
import domain.VIPBean;
import listener.ActionDoneListener;
import utils.ScreenUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddVIP extends JDialog {
    final int WIDTH = 400;
    final int HEIGHT = 400;
    private ActionDoneListener listener;
    public AddVIP(JFrame jf, String title, boolean isModel, ActionDoneListener listener){
        super(jf,title,isModel);
        this.listener = listener;
        //组装视图
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        this.setResizable(false);
        Box vBox = Box.createVerticalBox();

        //组装VIP名称
        Box nameBox = Box.createHorizontalBox();
        JLabel nameLable = new JLabel(" 会员名称:");
        JTextField nameField = new JTextField(15);

        nameBox.add(nameLable);
        nameBox.add(Box.createHorizontalStrut(20));
        nameBox.add(nameField);

        //组装会员性别
        Box genderBox = Box.createHorizontalBox();
        JLabel genderLable = new JLabel("         性别:");
        JTextField genderField = new JTextField(15);

        genderBox.add(genderLable);
        genderBox.add(Box.createHorizontalStrut(20));
        genderBox.add(genderField);

        //组装会员手机号
        Box phoneBox = Box.createHorizontalBox();
        JLabel phoneLable = new JLabel("     手机号:");
        JTextField phoneField = new JTextField(15);

        phoneBox.add(phoneLable);
        phoneBox.add(Box.createHorizontalStrut(20));
        phoneBox.add(phoneField);

        //组装会员身份证
        Box identificationBox = Box.createHorizontalBox();
        JLabel identificationLable = new JLabel("     身份证:");
        JTextField identificationField = new JTextField(15);

        identificationBox.add(identificationLable);
        identificationBox.add(Box.createHorizontalStrut(20));
        identificationBox.add(identificationField);

        //组装会员住址
        Box addressBox = Box.createHorizontalBox();
        JLabel addressLable = new JLabel("         住址:");
        JTextField addressField = new JTextField(15);

        addressBox.add(addressLable);
        addressBox.add(Box.createHorizontalStrut(20));
        addressBox.add(addressField);

        //组装会员邮编
        Box postcodeBox = Box.createHorizontalBox();
        JLabel postcodeLable = new JLabel("         邮编:");
        JTextField postcodeField = new JTextField(15);

        postcodeBox.add(postcodeLable);
        postcodeBox.add(Box.createHorizontalStrut(20));
        postcodeBox.add(postcodeField);

        //组装入会日期
        Box joinDateBox = Box.createHorizontalBox();
        JLabel joinDateLable = new JLabel(" 入会日期:");
        JTextField joinDateField = new JTextField(15);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String date = df.format(d);
        joinDateField.setText(date);
        joinDateBox.add(joinDateLable);
        joinDateBox.add(Box.createHorizontalStrut(20));
        joinDateBox.add(joinDateField);


        //组装添加按钮
        Box btnBox = Box.createHorizontalBox();
        JButton addBtn = new JButton("添加");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户的录入
                String name = nameField.getText().trim();
                if(name == null || name.equals("")){
                    JOptionPane.showMessageDialog(jf, "会员姓名不得为空！");
                    return;
                }

                String gender = genderField.getText().trim();
                String phone = phoneField.getText().trim();
                if(phone == null || phone.equals("")){
                    JOptionPane.showMessageDialog(jf, "会员手机号不得为空！");
                    return;
                }
                VIPDao dao = new VIPDao();
                VIPBean vip = dao.findByPhone(phone);
                if(vip!=null){
                    JOptionPane.showMessageDialog(jf, "此手机号已被绑定，请重新输入！");
                    return;
                }
                String identification = identificationField.getText().trim();
                String address = addressField.getText().trim();

                String postcode = postcodeField.getText().trim();
                String joinDate = joinDateField.getText().trim();
                vip = new VIPBean();
                vip.setName(name);
                vip.setGender(gender);
                vip.setPhone(phone);
                vip.setIdentification(identification);
                vip.setAddress(address);
                vip.setPostcode(postcode);
                vip.setJoinDate(joinDate);

                dao.add(vip);
                JOptionPane.showMessageDialog(jf,"添加成功");
                dispose();
                listener.done(null);
            }
        });

        btnBox.add(addBtn);

        vBox.add(Box.createVerticalStrut(20));
        vBox.add(nameBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(genderBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(phoneBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(identificationBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(addressBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(postcodeBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(joinDateBox);
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
