package cn.jianchen.com.trade.api.car.service;


import cn.jianchen.com.trade.api.car.model.Car;
import cn.jianchen.com.trade.common.exception.ServiceException;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface ICarService {

    List<Car> cars() throws Exception;

    Car car(int id);

    Car save(Car car) throws ServiceException;

    void remove(int id);

    List<Car> findByIds(List<Integer> ids);
}
