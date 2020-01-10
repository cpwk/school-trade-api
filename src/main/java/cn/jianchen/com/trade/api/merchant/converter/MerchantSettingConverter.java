package cn.jianchen.com.trade.api.merchant.converter;

import cn.jianchen.com.trade.api.merchant.model.MerchantSetting;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MerchantSettingConverter implements AttributeConverter<MerchantSetting, String> {
    @Override
    public String convertToDatabaseColumn(MerchantSetting attribute) {
        return JSON.toJSONString(attribute);
    }

    @Override
    public MerchantSetting convertToEntityAttribute(String dbData) {
        try {
            return JSONArray.parseObject(dbData, MerchantSetting.class);
        } catch (Exception e) {
            return null;
        }
    }
}
