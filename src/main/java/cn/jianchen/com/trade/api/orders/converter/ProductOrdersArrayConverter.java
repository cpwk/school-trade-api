package cn.jianchen.com.trade.api.orders.converter;


import cn.jianchen.com.trade.api.car.model.Car;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Converter(autoApply = true)
public class ProductOrdersArrayConverter implements AttributeConverter<List<Car>, String> {

    @Override
    public String convertToDatabaseColumn(List<Car> list) {
        return JSON.toJSONString(list);
    }

    @Override
    public List<Car> convertToEntityAttribute(String data) {
        try {
            return JSONArray.parseArray(data, Car.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
