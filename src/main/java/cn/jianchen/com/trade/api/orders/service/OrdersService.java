package cn.jianchen.com.trade.api.orders.service;


import cn.jianchen.com.trade.api.orders.model.Orders;
import cn.jianchen.com.trade.api.orders.qo.OrdersQo;
import cn.jianchen.com.trade.api.orders.repository.OrdersRepository;
import cn.jianchen.com.trade.common.context.Contexts;
import cn.jianchen.com.trade.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.jianchen.com.trade.common.entity.Constants.*;
import static cn.jianchen.com.trade.common.exception.ErrorCode.ERR_DATA_NOT_FOUND;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-31 20:59
 **/

@Service
public class OrdersService implements IOrdersService {

    @Autowired
    private OrdersRepository orderRepository;

    public Orders getById(Integer ordersId) throws ServiceException {

        Orders orders = orderRepository.findById(ordersId).orElse(null);

        if (orders == null) {
            throw new ServiceException(ERR_DATA_NOT_FOUND);
        }

        return orders;
    }

    @Override
    public void save(Orders order) throws ServiceException {

        order.setOrderNum(System.currentTimeMillis());

        Integer userId = Contexts.requestUserId();
        order.setUserId(userId);

        order.setStatus(WAIT_PAY);

        order.setCreatedAt(System.currentTimeMillis());

        orderRepository.save(order);
    }

    @Override
    public void pay(Orders order) throws ServiceException {

        Orders exist = getById(order.getId());

        exist.setStatus(WAIT_SURE_PRODUCT);

        orderRepository.save(exist);
    }

    @Override
    public void receive(Orders order) throws ServiceException {

        Orders exist = getById(order.getId());

        exist.setStatus(WAIT_COMMENT);

        orderRepository.save(exist);
    }

    @Override
    public void evalProduct(Orders order) throws ServiceException {

        Orders exist = getById(order.getId());

        exist.setStatus(COMMENTED);

        orderRepository.save(exist);
    }

    @Override
    public void remove(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Page<Orders> orders(OrdersQo qo, boolean admin) {
        return orderRepository.findAll(qo);
    }

    @Override
    public Orders order(int id) {
        return getById(id);
    }

    @Override
    public List<Orders> findByIdIs(List<Integer> ids) {
        return orderRepository.findByIdIs(ids);
    }
}
