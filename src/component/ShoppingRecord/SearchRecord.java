package component.ShoppingRecord;

import component.Commodity.SearchCommodity;
import component.VIPimformation.SearchVIP;
import component.VIPimformation.UpdateVIP;
import dao.CommodityDao.CommodityDao;
import dao.CommodityDao.ShoppingRecordDao;
import domain.CommodityBean;
import domain.ShoppingRecordBean;
import listener.ActionDoneListener;
import utils.ScreenUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class SearchRecord extends JDialog {
    final int WIDTH = 600;
    final int HEIGHT = 350;
    private JTable table;
    private Vector<String> titles;
    private Vector<Vector> tableData;
    private TableModel tableModel;
    private ActionDoneListener listener;
    public SearchRecord(JFrame jf, String title, boolean isModel, ActionDoneListener listener, String name){
        super(jf,title,isModel);
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

                String id = tableModel.getValueAt(selectedRow, 0).toString();

                //弹出一个对话框，让用户修改
                new UpdateRecord(jf, "修改订单信息", true, new ActionDoneListener() {
                    @Override
                    public void done(Object result) {
                        dispose();
                        listener.done(null);
                        new SearchRecord(jf, "查询订单", true, listener, name).setVisible(true);

                    }
                },id).setVisible(true);
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

                String id = tableModel.getValueAt(selectedRow, 0).toString();

                ShoppingRecordDao dao = new ShoppingRecordDao();
                dao.delete(id);
                JOptionPane.showMessageDialog(jf, "删除成功");

                dispose();
                listener.done(null);
                new SearchRecord(jf, "查询商品", true, listener, name).setVisible(true);
            }
        });
        //组装表格
        String[] ts = {"编号", "会员名称", "手机号", "商品名称", "单价", "购买数量", "总计","消费日期"};
        titles = new Vector<>();
        for(String x: ts){
            titles.add(x);
        }
        tableData = new Vector<>();
        ShoppingRecordDao dao = new ShoppingRecordDao();
       List<ShoppingRecordBean> rds = dao.findByName(name);
        for(ShoppingRecordBean rd: rds){
            Vector<Object> v = new Vector();
            v.add(rd.getId());
            v.add(rd.getVipname());
            v.add(rd.getPhone());
            v.add(rd.getComname());
            v.add(rd.getPrice());
            v.add(rd.getAmount());
            v.add(rd.getTotalMoney());
            v.add(rd.getDate());
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
