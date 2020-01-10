package cn.jianchen.com.trade.api.product.productItemarrayconverter;


import cn.jianchen.com.trade.api.product.entity.ProductItem;
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
public class ProductItemArrayConverter implements AttributeConverter<List<ProductItem>, String> {

    @Override
    public String convertToDatabaseColumn(List<ProductItem> list) {
        return JSON.toJSONString(list);
    }

    @Override
    public List<ProductItem> convertToEntityAttribute(String data) {
        try {
            return JSONArray.parseArray(data, ProductItem.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
