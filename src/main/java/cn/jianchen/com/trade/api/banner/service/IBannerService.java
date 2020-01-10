package cn.jianchen.com.trade.api.banner.service;


import cn.jianchen.com.trade.api.banner.model.Banner;
import cn.jianchen.com.trade.api.banner.qo.BannerQo;
import cn.jianchen.com.trade.common.exception.ServiceException;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface IBannerService {

    List<Banner> banners(BannerQo qo, boolean admin);

    Banner banner(int id);

    void save(Banner banner) throws ServiceException;

    void remove(int id);

    void outSome(List<Integer> ids);

    void putSome(List<Integer> ids);
}
