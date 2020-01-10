package cn.jianchen.com.trade.api.vip.converter;


import cn.jianchen.com.trade.api.vip.entity.PriceRule;
import com.alibaba.fastjson.JSON;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class PriceRuleConverter implements AttributeConverter<List<PriceRule>, String> {

    @Override
    public String convertToDatabaseColumn(List<PriceRule> priceRule) {
        return JSON.toJSONString(priceRule);
    }

    @Override
    public List<PriceRule> convertToEntityAttribute(String data) {
        try {
            return JSON.parseArray(data, PriceRule.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
