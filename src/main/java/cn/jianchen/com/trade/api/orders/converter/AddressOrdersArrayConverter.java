package cn.jianchen.com.trade.api.orders.converter;


import cn.jianchen.com.trade.api.address.model.Address;
import com.alibaba.fastjson.JSON;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Converter(autoApply = true)
public class AddressOrdersArrayConverter implements AttributeConverter<Address, String> {

    @Override
    public String convertToDatabaseColumn(Address address) {
        return JSON.toJSONString(address);
    }

    @Override
    public Address convertToEntityAttribute(String data) {
        try {
            return JSON.parseObject(data, Address.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
