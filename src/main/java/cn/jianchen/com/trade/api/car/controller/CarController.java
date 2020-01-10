package cn.jianchen.com.trade.api.car.controller;


import cn.jianchen.com.trade.api.car.model.Car;
import cn.jianchen.com.trade.api.car.service.CarService;
import cn.jianchen.com.trade.common.authority.AdminType;
import cn.jianchen.com.trade.common.authority.RequiredPermission;
import cn.jianchen.com.trade.common.controller.BaseController;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Controller
@RequestMapping("/user/car")
public class CarController extends BaseController {
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView save(String car) throws Exception {
        carService.save(parseModel(car, new Car()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView remove(Integer id) throws Exception {
        carService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/car")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView car(Integer id) throws Exception {
        return feedback(carService.car(id));
    }

    @RequestMapping(value = "/cars")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView cars() throws Exception {
        return feedback(carService.cars());
    }

    @RequestMapping(value = "/find_by_ids")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView findByIds(String ids) throws Exception {
        return feedback(carService.findByIds(JSON.parseArray(ids, Integer.class)));
    }
}