package cn.jianchen.com.trade.api.coupon.rulesconverter;


import cn.jianchen.com.trade.api.coupon.entity.CouponRule;
import com.alibaba.fastjson.JSON;
import com.sunnysuperman.commons.bean.Bean;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Converter(autoApply = true)
public class RulesConverter implements AttributeConverter<CouponRule, String> {

    @Override
    public String convertToDatabaseColumn(CouponRule list) {
        return JSON.toJSONString(list);
    }

    @Override
    public CouponRule convertToEntityAttribute(String data) {
        try {
            return Bean.fromJson(data, new CouponRule());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
