package cn.jianchen.com.trade.api.address.repository;


import cn.jianchen.com.trade.api.address.model.Address;
import cn.jianchen.com.trade.common.reposiotry.BaseRepository;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface IAddressRepository extends BaseRepository<Address, Integer> {
    List<Address> findByUserId(Integer userId);
}