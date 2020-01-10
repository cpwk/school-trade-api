package cn.jianchen.com.trade.api.product.service;


import cn.jianchen.com.trade.api.product.model.Product;
import cn.jianchen.com.trade.api.product.qo.ProductQo;
import cn.jianchen.com.trade.api.product.repository.IProductRepository;
import cn.jianchen.com.trade.api.sort.service.ISortService;
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
 * 创建时间:2019-11-01 10:05
 **/

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ISortService iSortService;

    public Product getById(Integer productId) throws ServiceException {

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            throw new ServiceException(ERR_DATA_NOT_FOUND);
        }

        return product;
    }

    @Override
    public Product product(int id) {
        return getById(id);
    }

    @Override
    public void save(Product product, boolean admin) throws ServiceException {
        if (admin) {
            productRepository.save(product);
        } else {
            product.setUserId(Contexts.requestUserId());
            productRepository.save(product);
        }
    }

    @Override
    public void remove(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public void outSome(List<Integer> ids) {

        List<Product> products = productRepository.findByIdIs(ids);

        for (Product product : products) {
            product.setStatus(PRODUCT_STOP_USED);
        }

        productRepository.saveAll(products);
    }

    @Override
    public void putSome(List<Integer> ids) {

        List<Product> products = productRepository.findByIdIs(ids);

        for (Product product : products) {
            product.setStatus(PRODUCT_USED);
        }

        productRepository.saveAll(products);
    }

    @Override
    public Page<Product> products_adm(ProductQo qo) {
        iSortService.codes2Ids(qo);
        return productRepository.findAll(qo);
    }

    @Override
    public Page<Product> products_usr(ProductQo qo) {
        qo.setUserId(Contexts.requestUserId());
        iSortService.codes2Ids(qo);
        return productRepository.findAll(qo);
    }

    @Override
    public Page<Product> products_by_type(ProductQo qo) {
        qo.setState(PASS);
        qo.setStatus(PRODUCT_USED);
        iSortService.codes2Ids(qo);
        return productRepository.findAll(qo);
    }

    @Override
    public Page<Product> products_home(ProductQo qo) {
        qo.setState(PASS);
        qo.setStatus(PRODUCT_USED);
        if (qo.getFirstSortId() != null) {
            iSortService.firstSortId2Ids(qo);
        }
        return productRepository.findAll(qo);
    }

    @Override
    public List<Product> findAllByProductIds(List<Integer> ids) {
        return productRepository.findAllByIdIn(ids);
    }


}