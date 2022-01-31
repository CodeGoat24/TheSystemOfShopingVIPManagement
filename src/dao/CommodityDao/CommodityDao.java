package dao.CommodityDao;

import JDBC.JDBCUtils;
import domain.CommodityBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommodityDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    public List<CommodityBean> findAll() {
        String sql = "select * from commonityimformation";
        List<CommodityBean> commonities = template.query(sql, new BeanPropertyRowMapper<CommodityBean>(CommodityBean.class));
        return commonities;
    }


    public void add(CommodityBean com) {
        String sql = "insert into commonityimformation values(?,?,?,?,?,?,?,?)";
        template.update(sql, com.getId(), com.getName(), com.getBirthPlace(), com.getBirthDay(), com.getPrice(),com.getStock(), com.getIntroduction(), com.getPs());

    }

    public CommodityBean findById(int id) {
        String sql = "select * from commonityimformation where id = ?";
        try{
            return template.queryForObject(sql, new BeanPropertyRowMapper<CommodityBean>(CommodityBean.class), id);
        }catch (Exception e){
            return null;
        }
    }

    public void upadate(CommodityBean com) {
        String sql = "update commonityimformation set name = ?, birthPlace = ?, birthDay = ?, price = ?, stock = ?, introduction = ?, ps = ? where id = ?";
        template.update(sql, com.getName(), com.getBirthPlace(), com.getBirthDay(), com.getPrice(), com.getStock(), com.getIntroduction(), com.getPs(), com.getId());
    }

    public void delete(int id) {
        String sql = "delete from commonityimformation where id = ?";
        template.update(sql, id);
    }

    public List<CommodityBean> findByName(String name) {
        String sql = "select * from commonityimformation where name like '%" + name +"%'";

        return template.query(sql, new BeanPropertyRowMapper<CommodityBean>(CommodityBean.class));

    }

    public CommodityBean findByNameForPrice(String name) {
        String sql = "select * from commonityimformation where name = ?";
        try{
            return template.queryForObject(sql, new BeanPropertyRowMapper<CommodityBean>(CommodityBean.class), name);
        }catch (Exception e){
            return null;
        }

    }
}
