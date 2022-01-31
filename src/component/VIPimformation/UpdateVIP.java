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

public class UpdateVIP extends JDialog{
    final int WIDTH = 400;
    final int HEIGHT = 400;
    private String phone;
    private ActionDoneListener listener;
    private JTextField nameField;
    private JTextField genderField;
    private JTextField phoneField;
    private JTextField identificationField;
    private JTextField addressField;
    private JTextField postcodeField;
    private JTextField joinDateField;

    public UpdateVIP(JFrame jf, String title, boolean isModel, ActionDoneListener listener, String phone){
        super(jf,title,isModel);
        this.listener  = listener;
        this.phone = phone;
        VIPDao dao = new VIPDao();
        VIPBean vip = dao.findByPhone(phone);
//        组装试图
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);

        Box vBox = Box.createVerticalBox();

        //组装会员姓名
        Box nameBox = Box.createHorizontalBox();
        JLabel nameLable = new JLabel("会员姓名：");
        nameField = new JTextField(15);
        nameField.setText("" + vip.getName());

        nameBox.add(nameLable);
        nameBox.add(Box.createHorizontalStrut(20));
        nameBox.add(nameField);

        //组装会员性别
        Box genderBox = Box.createHorizontalBox();
        JLabel genderLable = new JLabel("         性别：");
        genderField = new JTextField(15);
        genderField.setText(vip.getGender());

        genderBox.add(genderLable);
        genderBox.add(Box.createHorizontalStrut(20));
        genderBox.add(genderField);

        //组装会员手机号
        Box phoneBox = Box.createHorizontalBox();
        JLabel phoneLable = new JLabel("    手机号：");
        phoneField = new JTextField(15);
        phoneField.setText(vip.getPhone());
        phoneField.setEnabled(false);
        phoneBox.add(phoneLable);
        phoneBox.add(Box.createHorizontalStrut(20));
        phoneBox.add(phoneField);

        //组装会员身份证
        Box identificationBox = Box.createHorizontalBox();
        JLabel identificationLable = new JLabel("    身份证：");
        identificationField = new JTextField(15);
        identificationField.setText(vip.getIdentification());

        identificationBox.add(identificationLable);
        identificationBox.add(Box.createHorizontalStrut(20));
        identificationBox.add(identificationField);

        //组装住址
        Box addressBox = Box.createHorizontalBox();
        JLabel addressLable = new JLabel("        住址：");
        addressField = new JTextField(15);
        addressField.setText("" + vip.getAddress());

        addressBox.add(addressLable);
        addressBox.add(Box.createHorizontalStrut(20));
        addressBox.add(addressField);

        //组装邮编
        Box postcodeBox = Box.createHorizontalBox();
        JLabel postcodeLable = new JLabel("         邮编：");
        postcodeField = new JTextField(15);
        postcodeField.setText("" + vip.getPostcode());

        postcodeBox.add(postcodeLable);
        postcodeBox.add(Box.createHorizontalStrut(20));
        postcodeBox.add(postcodeField);

        //组装入会日期
        Box joinDateBox = Box.createHorizontalBox();
        JLabel joinDateLable = new JLabel("入会日期：");
        joinDateField = new JTextField(15);
        joinDateField.setText(vip.getJoinDate());

        joinDateBox.add(joinDateLable);
        joinDateBox.add(Box.createHorizontalStrut(20));
        joinDateBox.add(joinDateField);


        Box btnBox = Box.createHorizontalBox();
        JButton updateBtn = new JButton("修改");

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户修改后在输入框中输入的内容
                //获取用户的录入
                String name = nameField.getText().trim();
                if(name == null || name.equals("")){
                    JOptionPane.showMessageDialog(jf, "会员姓名不得为空！");
                    return;
                }

                String gender = genderField.getText().trim();
                String phone = phoneField.getText().trim();

                String identification = identificationField.getText().trim();
                String address = addressField.getText().trim();

                String postcode = postcodeField.getText().trim();
                String joinDate = joinDateField.getText().trim();
                VIPBean vip = new VIPBean();
                vip.setName(name);
                vip.setGender(gender);
                vip.setPhone(phone);
                vip.setIdentification(identification);
                vip.setAddress(address);
                vip.setPostcode(postcode);
                vip.setJoinDate(joinDate);
                VIPDao dao = new VIPDao();
                dao.upadate(vip);
                JOptionPane.showMessageDialog(jf,"修改成功");
                dispose();
                listener.done(null);
            }
        });

        btnBox.add(updateBtn);

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
