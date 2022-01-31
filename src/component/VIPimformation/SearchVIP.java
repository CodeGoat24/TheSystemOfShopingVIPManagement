package component.VIPimformation;

import dao.CommodityDao.CommodityDao;
import dao.CommodityDao.VIPDao;
import domain.CommodityBean;
import domain.VIPBean;
import listener.ActionDoneListener;
import utils.ScreenUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class SearchVIP extends JDialog {
    final int WIDTH = 600;
    final int HEIGHT = 350;
    private JTable table;
    private Vector<String> titles;
    private Vector<Vector> tableData;
    private TableModel tableModel;
    private ActionDoneListener listener;
    public SearchVIP(JFrame jf, String titl, boolean isModel, ActionDoneListener listener, String name){
        super(jf,titl,isModel);
        this.listener = listener;
        //组装视图
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        this.setResizable(false);

        Box vBox = Box.createVerticalBox();
        Box nameBox = Box.createHorizontalBox();

        JLabel nameLable = new JLabel("会员名称含：" + name);
        JButton updateBtn = new JButton("修改");
        JButton deleteBtn = new JButton("删除");
        nameBox.add(nameLable);
        nameBox.add(Box.createHorizontalStrut(20));
        nameBox.add(updateBtn);
        nameBox.add(deleteBtn);
        vBox.add(nameBox);

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
                        dispose();
                        listener.done(null);
                        new SearchVIP(jf, "查询会员", true, listener, name).setVisible(true);

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
                dispose();
                listener.done(null);
                new SearchVIP(jf, "查询会员", true, listener, name).setVisible(true);


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
        List<VIPBean> vips = dao.findByName(name);
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
        vBox.add(jsp);
        this.add(vBox);
    }
}
