package component.Commodity;

import dao.CommodityDao.CommodityDao;
import domain.CommodityBean;
import listener.ActionDoneListener;
import utils.ScreenUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class SearchCommodity extends JDialog {
    final int WIDTH = 700;
    final int HEIGHT = 350;
    private JTable table;
    private Vector<String> titles;
    private Vector<Vector> tableData;
    private TableModel tableModel;
    private ActionDoneListener listener;
    public SearchCommodity(JFrame jf, String title, boolean isModel, ActionDoneListener listener, String name){
        super(jf,title,isModel);
        this.listener = listener;
        //组装视图
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        this.setResizable(false);

        Box vBox = Box.createVerticalBox();
        Box nameBox = Box.createHorizontalBox();

        JLabel nameLable = new JLabel("商品名称含：" + name);
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
                new UpdateCommodity(jf, "修改商品", true, new ActionDoneListener() {
                    @Override
                    public void done(Object result) {
                        dispose();
                        listener.done(null);
                        new SearchCommodity(jf, "查询商品", true,listener, name).setVisible(true);

                    }
                },id).setVisible(true);
            }
            })
        ;
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

                CommodityDao dao = new CommodityDao();
                dao.delete(Integer.parseInt(id));
                JOptionPane.showMessageDialog(jf, "删除成功");

                dispose();
                listener.done(null);
                new SearchCommodity(jf, "查询商品", true, listener, name).setVisible(true);
            }
        });
        //组装表格
        String[] ts = {"编号", "名称", "制造商", "生产日期", "价格", "库存", "简介"};
        titles = new Vector<>();
        for(String x: ts){
            titles.add(x);
        }
        tableData = new Vector<>();
        CommodityDao dao = new CommodityDao();
        List<CommodityBean> comonities = dao.findByName(name);
        for(CommodityBean cb: comonities){
            Vector<Object> v = new Vector();
            v.add(cb.getId());
            v.add(cb.getName());
            v.add(cb.getBirthPlace());
            v.add(cb.getBirthDay());
            v.add(cb.getPrice());
            v.add(cb.getStock());
            v.add(cb.getIntroduction());
            v.add(cb.getPs());
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




    };

