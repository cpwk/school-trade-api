package cn.jianchen.com.trade.api.orders.repository;


import cn.jianchen.com.trade.api.orders.model.Orders;
import cn.jianchen.com.trade.common.reposiotry.BaseRepository;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface OrdersRepository extends BaseRepository<Orders, Integer> {

    List<Orders> findByIdIn(List<Integer> ids);

}