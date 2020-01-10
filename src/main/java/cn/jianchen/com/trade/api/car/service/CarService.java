package cn.jianchen.com.trade.api.car.service;


import cn.jianchen.com.trade.api.car.model.Car;
import cn.jianchen.com.trade.api.car.repository.ICarRepository;
import cn.jianchen.com.trade.api.product.model.Product;
import cn.jianchen.com.trade.api.product.service.ProductService;
import cn.jianchen.com.trade.common.context.Contexts;
import cn.jianchen.com.trade.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.jianchen.com.trade.common.exception.ErrorCode.ERR_DATA_NOT_FOUND;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-31 20:59
 **/

@Service
public class CarService implements ICarService {

    @Autowired
    private ICarRepository carRepository;

    @Autowired
    private ProductService productService;

    public Car getById(Integer carId) throws ServiceException {

        Car car = carRepository.findById(carId).orElse(null);

        if (car == null) {
            throw new ServiceException(ERR_DATA_NOT_FOUND);
        }

        return car;
    }

    @Override
    public Car save(Car car) throws ServiceException {

        Integer userId = Contexts.requestUserId();

        Car exist = carRepository.findByUserIdAndProductIdAndSno(userId, car.getProductId(), car.getSno());

        if (car.getId() == null) {
            if (exist == null) {
                car.setUserId(userId);
                carRepository.save(car);
                return car;
            } else {
                exist.setNum(exist.getNum() + car.getNum());
                carRepository.save(exist);
                return exist;
            }
        } else {
            if (exist == null) {
                car.setUserId(userId);
                carRepository.save(car);
                return car;
            } else {
                if (!exist.getId().equals(car.getId())) {
                    exist.setNum(exist.getNum() + car.getNum());
                    carRepository.save(exist);
                    carRepository.deleteById(car.getId());
                    return exist;
                } else {
                    exist.setNum(car.getNum());
                    carRepository.save(exist);
                    return exist;
                }
            }
        }
    }

    @Override
    public void remove(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> cars() throws Exception {

        Integer userId = Contexts.requestUserId();

        List<Car> c = carRepository.findByUserId(userId);

        List<Integer> ids = new ArrayList<>(c.size());

        for (Car car : c) {
            ids.add(car.getProductId());
        }

        List<Product> products = productService.findAllByProductIds(ids);

        Map<Integer, Product> map = new HashMap<>(products.size());

        for (Product product : products) {
            map.put(product.getId(), product);
        }

        for (Car car : c) {
            car.setProduct(map.get(car.getProductId()));
        }

        return c;
    }

    @Override
    public Car car(int id) {
        return getById(id);
    }

    @Override
    public List<Car> findByIds(List<Integer> ids) {

        List<Car> cars = carRepository.findAllByIdIn(ids);

        List<Integer> productIds = new ArrayList<>(cars.size());

        for (Car car : cars) {
            productIds.add(car.getProductId());
        }

        List<Product> products = productService.findAllByProductIds(productIds);

        Map<Integer, Product> map = new HashMap<>(products.size());

        for (Product product : products) {
            map.put(product.getId(), product);
        }
        for (Car car : cars) {
            car.setProduct(map.get(car.getProductId()));
        }
        return cars;
    }
}
