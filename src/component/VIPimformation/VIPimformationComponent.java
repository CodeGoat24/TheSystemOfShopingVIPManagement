package component.VIPimformation;

import component.Commodity.AddCommodity;
import component.Commodity.CommodityManageComponent;
import component.Commodity.SearchCommodity;
import component.Commodity.UpdateCommodity;
import dao.CommodityDao.CommodityDao;
import dao.CommodityDao.VIPDao;
import domain.CommodityBean;
import domain.VIPBean;
import listener.ActionDoneListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class VIPimformationComponent extends Box {
    final int WIDTH = 850;
    final int HEIGHT = 600;
    JFrame jf = null;
    private JTable table;
    private Vector<String> titles;
    private Vector<Vector> tableData;
    private TableModel tableModel;
    private JSplitPane sp;
    public VIPimformationComponent(JSplitPane sp) {
        super(BoxLayout.Y_AXIS);
        JPanel btnPanel = new JPanel();
//        Color color = new Color(203, 220, 217);
//
//        btnPanel.setBackground(color);
        btnPanel.setMaximumSize(new Dimension(WIDTH, 80));
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton addBtn = new JButton("添加");
        JButton updateBtn = new JButton("修改");
        JButton deleteBtn = new JButton("删除");
        JButton searchBtn = new JButton("查询");
        JLabel label = new JLabel("VIP信息管理");
        JLabel label2 = new JLabel("VIP名称:");
        JTextField seachName = new JTextField(15);
        btnPanel.add(label);
        btnPanel.add(Box.createHorizontalStrut(200));
        btnPanel.add(label2);
        btnPanel.add(seachName);
        btnPanel.add(searchBtn);
        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);

        this.add(btnPanel);

        //添加按钮监听
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddVIP(jf, "注册会员", true, new ActionDoneListener() {
                    @Override
                    public void done(Object x) {
                        sp.setRightComponent(new VIPimformationComponent(sp));
                        //刷新表格
                    }
                }
                ).setVisible(true);

            }
        });
        //查询按钮监听
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = seachName.getText();
                if(name == null || name.equals("")){
                    JOptionPane.showMessageDialog(jf, "请输入会员姓名");
                    return;
                }
                new SearchVIP(jf, "查询会员", true, new ActionDoneListener() {
                    @Override
                    public void done(Object x) {
                        sp.setRightComponent(new VIPimformationComponent(sp));
                    }
                }, name).setVisible(true);
            }
        });

        //修改按钮监听
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取当前表格选中的id
                int selectedRow = table.getSelectedRow();//如果有选中的条目，则返回条目的行号，如果没有选中，那么返回-1

                if (selectedRow==-1){
                    JOptionPane.showMessageDialog(jf,"请选择要修改的条目！");
                    return;
                }

                String phone = tableModel.getValueAt(selectedRow, 2).toString();

                //弹出一个对话框，让用户修改
                new UpdateVIP(jf, "修改会员信息", true, new ActionDoneListener() {
                    @Override
                    public void done(Object result) {
                        sp.setRightComponent(new VIPimformationComponent(sp));
                    }
                },phone).setVisible(true);
            }
        });
        //删除按钮监听
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取选中的条目
                int selectedRow = table.getSelectedRow();//如果有选中的条目，则返回条目的行号，如果没有选中，那么返回-1

                if (selectedRow==-1){
                    JOptionPane.showMessageDialog(jf,"请选择要删除的条目！");
                    return;
                }

                //防止误操作
                int result = JOptionPane.showConfirmDialog(jf, "确认要删除选中的条目吗？", "确认删除", JOptionPane.YES_NO_OPTION);
                if (result != JOptionPane.YES_OPTION){
                    return;
                }

                String phone = tableModel.getValueAt(selectedRow, 2).toString();

                VIPDao dao = new VIPDao();
                dao.delete(phone);
                JOptionPane.showMessageDialog(jf, "删除成功");
                sp.setRightComponent(new VIPimformationComponent(sp));

            }
        });
        //组装表格
        String[] ts = {"姓名", "性别", "手机号", "身份证", "住址", "邮编号码", "入会时间"};
        titles = new Vector<>();
        for(String title: ts){
            titles.add(title);
        }
        tableData = new Vector<>();
        VIPDao dao = new VIPDao();
        List<VIPBean> vips = dao.findAll();
        for(VIPBean cb: vips){
            Vector<Object> v = new Vector();
            v.add(cb.getName());
            v.add(cb.getGender());
            v.add(cb.getPhone());
            v.add(cb.getIdentification());
            v.add(cb.getAddress());
            v.add(cb.getPostcode());
            v.add(cb.getJoinDate());
            tableData.add(v);
        }
        tableModel = new DefaultTableModel(tableData, titles);

        table = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //只能选中一行
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jsp = new JScrollPane(table);
        this.add(jsp);
    }
}
