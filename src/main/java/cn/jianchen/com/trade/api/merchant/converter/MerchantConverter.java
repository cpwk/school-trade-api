package cn.jianchen.com.trade.api.merchant.converter;

import cn.jianchen.com.trade.api.merchant.model.Merchant;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MerchantConverter implements AttributeConverter<Merchant, String> {

    @Override
    public String convertToDatabaseColumn(Merchant obj) {
        return JSON.toJSONString(obj);
    }

    @Override
    public Merchant convertToEntityAttribute(String data) {
        try {
            return JSONArray.parseObject(data, Merchant.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
