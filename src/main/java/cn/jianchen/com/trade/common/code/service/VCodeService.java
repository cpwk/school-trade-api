package cn.jianchen.com.trade.common.code.service;


import cn.jianchen.com.trade.common.code.model.VCode;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * <p>
 * 类名称:VCodeService
 * 类描述:图片验证码服务层接口
 **/

public interface VCodeService {

    void saveVCode(VCode vCode);

    VCode getVCode(Long key) throws Exception;
}
