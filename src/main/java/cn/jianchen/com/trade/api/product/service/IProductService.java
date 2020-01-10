package cn.jianchen.com.trade.api.product.service;


import cn.jianchen.com.trade.api.product.model.Product;
import cn.jianchen.com.trade.api.product.qo.ProductQo;
import cn.jianchen.com.trade.common.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface IProductService {

    Page<Product> products_by_type(ProductQo qo);

    Page<Product> products_adm(ProductQo qo);

    Page<Product> products_usr(ProductQo qo);

    Page<Product> products_home(ProductQo qo);

    Product product(int id);

    void save(Product product, boolean admin) throws ServiceException;

    void remove(int id);

    void outSome(List<Integer> ids);

    void putSome(List<Integer> ids);

    List<Product> findAllByProductIds(List<Integer> ids);

}
