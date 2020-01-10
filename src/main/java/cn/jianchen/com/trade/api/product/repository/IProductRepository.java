package cn.jianchen.com.trade.api.product.repository;


import cn.jianchen.com.trade.api.product.model.Product;
import cn.jianchen.com.trade.common.reposiotry.BaseRepository;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface IProductRepository extends BaseRepository<Product, Integer> {

    List<Product> findAllByIdIn(List<Integer> ids);

    List<Product> findByIdIs(List<Integer> ids);

    List<Product> findAllBySortIdIn(List<String> codes);

}