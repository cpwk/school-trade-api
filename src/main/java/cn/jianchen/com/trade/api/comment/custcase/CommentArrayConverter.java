package cn.jianchen.com.trade.api.comment.custcase;


import cn.jianchen.com.trade.api.comment.entity.CommentItems;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Converter(autoApply = true)
public class CommentArrayConverter implements AttributeConverter<CommentItems, String> {
    @Override
    public String convertToDatabaseColumn(CommentItems obj) {
        return JSON.toJSONString(obj);
    }

    @Override
    public CommentItems convertToEntityAttribute(String data) {
        try {
            return JSONArray.parseObject(data, CommentItems.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
