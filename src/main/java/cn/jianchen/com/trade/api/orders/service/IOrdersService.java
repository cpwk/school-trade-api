package cn.jianchen.com.trade.api.orders.service;


import cn.jianchen.com.trade.api.orders.model.Orders;
import cn.jianchen.com.trade.api.orders.qo.OrdersQo;
import cn.jianchen.com.trade.common.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface IOrdersService {

    Page<Orders> orders(OrdersQo qo, boolean admin);

    Orders order(int id);

    void save(Orders order) throws ServiceException;

    void pay(Orders order) throws ServiceException;

    void receive(Orders order) throws ServiceException;

    void evalProduct(Orders order) throws ServiceException;

    void remove(int id);

    List<Orders> findByIdIn(List<Integer> ids);

}
