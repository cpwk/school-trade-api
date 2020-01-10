package cn.jianchen.com.trade.api.address.controller;

import cn.jianchen.com.trade.api.address.model.Address;
import cn.jianchen.com.trade.api.address.qo.AddressQo;
import cn.jianchen.com.trade.api.address.service.AddressService;
import cn.jianchen.com.trade.common.authority.AdminType;
import cn.jianchen.com.trade.common.authority.RequiredPermission;
import cn.jianchen.com.trade.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 **/

@Controller
@RequestMapping("/user/address")
public class AddressController extends BaseController {
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView save(String address) throws Exception {
        addressService.save(parseModel(address, new Address()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView remove(Integer id) throws Exception {
        addressService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/address")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView address(Integer id) throws Exception {
        return feedback(addressService.address(id));
    }

    @RequestMapping(value = "/find_address_to_list")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView find_address_to_list(String addressQo) throws Exception {
        return feedback(addressService.findAddressToList(parseModel(addressQo, new AddressQo()), true));
    }

    @RequestMapping(value = "/def")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView def(Integer id) throws Exception {
        addressService.def(id);
        return feedback(null);
    }

}