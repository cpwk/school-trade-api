package cn.jianchen.com.trade.api.car.repository;


import cn.jianchen.com.trade.api.car.model.Car;
import cn.jianchen.com.trade.common.reposiotry.BaseRepository;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface ICarRepository extends BaseRepository<Car, Integer> {

    List<Car> findByUserId(Integer userId);

    List<Car> findAllByIdIn(List<Integer> ids);

    Car findByUserIdAndProductIdAndSno(Integer userId, Integer productId, Integer sno);

}