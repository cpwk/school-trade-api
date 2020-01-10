package cn.jianchen.com.trade.api.address.service;


import cn.jianchen.com.trade.api.address.model.Address;
import cn.jianchen.com.trade.api.address.qo.AddressQo;
import cn.jianchen.com.trade.common.exception.ServiceException;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface IAddressService {

    List<Address> findAddressToList(AddressQo addressQo, boolean admin);

    Address address(Integer addressId);

    void save(Address address) throws Exception;

    void remove(Integer addressId) throws ServiceException;

    void def(Integer id) throws Exception;
}
