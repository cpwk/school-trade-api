package cn.jianchen.com.trade.api.sort.service;


import cn.jianchen.com.trade.api.product.qo.ProductQo;
import cn.jianchen.com.trade.api.sort.model.Sort;
import cn.jianchen.com.trade.common.exception.ServiceException;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface ISortService {

    List<Sort> sorts(boolean adm);

    Sort sort(int id) throws Exception;

    void save(Sort sort) throws ServiceException;

    void status(int id, byte status) throws ServiceException;

    void remove(int id) throws ServiceException;

    void codes2Ids(ProductQo qo);

    void firstSortId2Ids(ProductQo qo);

}