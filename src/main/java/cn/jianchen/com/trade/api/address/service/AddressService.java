package cn.jianchen.com.trade.api.address.service;

import cn.jianchen.com.trade.api.address.model.Address;
import cn.jianchen.com.trade.api.address.qo.AddressQo;
import cn.jianchen.com.trade.api.address.repository.IAddressRepository;
import cn.jianchen.com.trade.common.context.Contexts;
import cn.jianchen.com.trade.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cn.jianchen.com.trade.common.entity.Constants.IS_DEF;
import static cn.jianchen.com.trade.common.entity.Constants.NOT_DEF;
import static cn.jianchen.com.trade.common.exception.ErrorCode.ERR_DATA_NOT_FOUND;
import static cn.jianchen.com.trade.common.exception.ErrorCode.ERR_ID_NULL;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Service
public class AddressService implements IAddressService {

    @Autowired
    private IAddressRepository iAddressRepository;

    public Address getById(Integer addressId) throws ServiceException {

        Address address = iAddressRepository.findById(addressId).orElse(null);

        if (address == null) {
            throw new ServiceException(ERR_DATA_NOT_FOUND);
        }

        return address;
    }

    @Override
    public void save(Address address) throws Exception {

        Integer userId = Contexts.requestUserId();

        address.setUserId(userId);

        address = iAddressRepository.save(address);

        def(address.getId());
    }

    @Override
    public void remove(Integer addressId) throws ServiceException {

        if (addressId == null) {
            throw new ServiceException(ERR_ID_NULL);
        }

        iAddressRepository.deleteById(addressId);
    }

    @Override
    public List<Address> findAddressToList(AddressQo addressQo, boolean admin) {

        addressQo.setUserId(Contexts.requestUserId());

        return iAddressRepository.findAll(addressQo);
    }

    @Override
    public Address address(Integer addressId) {
        return getById(addressId);
    }

    @Override
    public void def(Integer id) throws Exception {

        List<Address> addresses = iAddressRepository.findByUserId(Contexts.requestUserId());

        List<Address> addressList = new ArrayList<>(addresses.size());

        for (Address a : addresses) {
            if (a != null) {
                a.setDef(NOT_DEF);
                addressList.add(a);
            }
        }

        iAddressRepository.saveAll(addressList);

        Address newDef = getById(id);

        if (newDef != null) {
            newDef.setDef(IS_DEF);
            iAddressRepository.save(newDef);
        }
    }
}