package dao.CommodityDao;

import JDBC.JDBCUtils;
import domain.CommodityBean;
import domain.VIPBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class VIPDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    public List<VIPBean> findAll() {
        String sql = "select * from vipimformation";
        List<VIPBean> vips = template.query(sql, new BeanPropertyRowMapper<VIPBean>(VIPBean.class));
        return vips;
    }


    public void add(VIPBean vip) {
        String sql = "insert into vipimformation values(?,?,?,?,?,?,?)";
        template.update(sql, vip.getName(), vip.getGender(), vip.getPhone(), vip.getIdentification(), vip.getAddress(), vip.getPostcode(), vip.getJoinDate());

    }

    public VIPBean findByPhone(String phone) {
        String sql = "select * from vipimformation where phone = ?";
        try{
            return template.queryForObject(sql, new BeanPropertyRowMapper<VIPBean>(VIPBean.class), phone);
        }catch (Exception e){
            return null;
        }
    }

    public void upadate(VIPBean vip) {
        String sql = "update vipimformation set name = ?, gender = ?,  identification = ?, address = ?, postcode = ?, joinDate = ? where phone = ?";
        template.update(sql, vip.getName(), vip.getGender(), vip.getIdentification(), vip.getAddress(), vip.getPostcode(), vip.getJoinDate(), vip.getPhone());
    }

    public void delete(String phone) {
        String sql = "delete from vipimformation where phone = ?";
        template.update(sql, phone);
    }

    public List<VIPBean> findByName(String name) {
        String sql = "select * from vipimformation where name like '%" + name +"%'";

        return template.query(sql, new BeanPropertyRowMapper<VIPBean>(VIPBean.class));

    }
}
