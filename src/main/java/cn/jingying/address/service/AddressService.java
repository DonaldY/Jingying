package cn.jingying.address.service;

import cn.jingying.address.dao.AddressDao;
import cn.jingying.address.model.Address;
import cn.jingying.common.ServerResponse;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DonaldY on 2018/3/18.
 */
public class AddressService {
    private final AddressDao addressDao = new AddressDao();
    
    public ServerResponse add(Integer userId, Address address) {
        address.set("user_id", userId);
        // TODO: 测试 id 是否自增
        boolean isSuccess = this.addressDao.add(address);
        if (isSuccess) {
            return ServerResponse.createBySuccess("新建地址成功");
        }
        return ServerResponse.createByErrorMessage("新建地址失败");
    }
    
    public ServerResponse delete (Integer userId, Integer addressId) {
        /**
         * 防止横向越权，要userId 与 addressId绑定
         */
        int count = this.addressDao.deleteByUidAndAddreid(userId, addressId);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("删除地址成功");
        }
        return ServerResponse.createByErrorMessage("删除地址失败");
    }

    public ServerResponse update(Integer userId, Address address) {
        boolean isSuccess = this.addressDao.updateByUid(userId, address);
        if (isSuccess) {
            return ServerResponse.createBySuccessMessage("更新地址成功");
        }
        return ServerResponse.createByErrorMessage("更新地址失败");
    }

    public ServerResponse<Address> getAddressById(Integer userId, Integer addressId) {

        Address address = this.addressDao.queryByUidAndAddreid(userId, addressId);
        
        if (null != address) {
            return ServerResponse.createBySuccess("查询成功", address);
        }
        return ServerResponse.createByErrorMessage("查询失败");
    }

    public ServerResponse<List<Record>> list(Integer userId, Integer pageNum, Integer pageSize) {
        
        List pages = this.addressDao.queryAll(userId, pageNum, pageSize);
        return ServerResponse.createBySuccess(pages);
    }

    public ServerResponse<Address> getCheckedAddress(Integer id) {
        Address address = this.addressDao.queryCheckedAddress(id);
        return ServerResponse.createBySuccess(address);
    }

    public ServerResponse updateChecked(Integer userId, Integer addressId) {
        int isSuccess = this.addressDao.updateChecked(userId, addressId);
        if (0 == isSuccess) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess();
    }

    public ServerResponse updateDefault(Integer userId, Integer addressId) {
        int isSuccess = this.addressDao.updateDefault(userId, addressId);
        if (0 == isSuccess) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess();
    }
}
