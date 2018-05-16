package cn.jingying.address.dao;

import cn.jingying.address.model.Address;
import cn.jingying.utils.DateTimeUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.Date;
import java.util.List;

/**
 * Created by DonaldY on 2018/3/18.
 */
public class AddressDao {
    private final Address dao = new Address().dao();
    
    public boolean add(Address address) {
        // TODO : 测试
        return address.save();
    }

    public int deleteByUidAndAddreid(Integer userId, Integer addressId) {
        // TODO : 测试
        return Db.update("UPDATE j_address SET status=0 WHERE user_id=? AND id=?", userId, addressId);
    }

    public boolean updateByUid(Integer userId, Address address) {
        address.set("update_time", DateTimeUtil.dateToStr(new Date()));
        return address.update();
        
    }

    public Address queryByUidAndAddreid(Integer userId, Integer addressId) {
        String sql = "SELECT id, rece_name, rece_phone, rece_address FROM j_address WHERE id=? And user_id=?";
        return this.dao.findFirst(sql, addressId, userId);
        
    }

    public List<Record> queryAll(Integer userId, Integer pageNum, Integer pageSize) {
        // TODO : 测试
        String select = "SELECT id, rece_name, rece_phone, rece_address, rece_default, checked ";
        String sqlExceptSelect = "FROM j_address WHERE user_id=? AND status=1 ORDER BY rece_default DESC";
        /*return Db.paginate(pageNum, pageSize, select, sqlExceptSelect,userId);*/
        return Db.find(select + sqlExceptSelect, userId);
    }

    public Address queryCheckedAddress(Integer id) {
        // TODO : 省 城市 县级 村，四级联动
        String sql = "SELECT id, rece_name, rece_phone, rece_address, `rece_default` FROM j_address WHERE user_id=? " +
            "AND checked=1";
        return this.dao.findFirst(sql, id);
    }

    public int updateChecked(Integer userId, Integer addressId) {
        Db.update("UPDATE j_address SET checked=0 WHERE user_id=? AND id<>?", userId, addressId);
        return Db.update("UPDATE j_address SET checked=1 WHERE user_id=? AND id=?", userId, addressId);
    }

    public int updateDefault(Integer userId, Integer addressId) {
        Db.update("UPDATE j_address SET `rece_default`=0 WHERE user_id=? AND id<>?", userId, addressId);
        return Db.update("UPDATE j_address SET `rece_default`=1 WHERE user_id=? AND id=?", userId, addressId);
    }
}
