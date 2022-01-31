package dao.CommodityDao;

import JDBC.JDBCUtils;
import domain.ShoppingRecordBean;
import domain.VIPBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ShoppingRecordDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    public List<ShoppingRecordBean> findAll() {
        String sql = "select * from shoppingrecord";
        List<ShoppingRecordBean> records = template.query(sql, new BeanPropertyRowMapper<ShoppingRecordBean>(ShoppingRecordBean.class));
        return records;
    }


    public void add(ShoppingRecordBean rd) {
        String sql = "insert into shoppingrecord values(?,?,?,?,?,?,?,?)";
        template.update(sql, rd.getId(), rd.getVipname(), rd.getPhone(), rd.getComname(), rd.getPrice(), rd.getAmount(), rd.getTotalMoney(), rd.getDate());

    }

    public ShoppingRecordBean findById(String id) {
        String sql = "select * from shoppingrecord where id = ?";
        try{
            return template.queryForObject(sql, new BeanPropertyRowMapper<ShoppingRecordBean>(ShoppingRecordBean.class), id);
        }catch (Exception e){
            return null;
        }
    }

    public void upadate(ShoppingRecordBean rd) {
        String sql = "update shoppingrecord set id = ?, vipname = ?, phone = ?, comname = ?, price = ?, amount = ?, totalMoney = ?, date = ? where id = ?";
        template.update(sql, rd.getId(), rd.getVipname(), rd.getPhone(), rd.getComname(), rd.getPrice(), rd.getAmount(), rd.getTotalMoney(), rd.getDate(), rd.getId());
    }

    public void delete(String id) {
        String sql = "delete from shoppingrecord where id = ?";
        template.update(sql, id);
    }

    public List<ShoppingRecordBean> findByName(String name) {
        String sql = "select * from shoppingrecord where vipname like '%" + name +"%'";

        return template.query(sql, new BeanPropertyRowMapper<ShoppingRecordBean>(ShoppingRecordBean.class));

    }
}
