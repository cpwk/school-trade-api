package cn.jianchen.com.trade.api.product.controller;


import cn.jianchen.com.trade.api.admin.authority.AdminPermission;
import cn.jianchen.com.trade.api.product.model.Product;
import cn.jianchen.com.trade.api.product.qo.ProductQo;
import cn.jianchen.com.trade.api.product.service.IProductService;
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
@RequestMapping("/adm/product")
public class ProductAdmController extends BaseController {

    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.PRODUCT_EDIT)
    public ModelAndView save(String product) throws Exception {
        productService.save(parseModel(product, new Product()), true);
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.PRODUCT_EDIT)
    public ModelAndView remove(Integer id) throws Exception {
        productService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/product")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.PRODUCT_EDIT)
    public ModelAndView product(Integer id) throws Exception {
        return feedback(productService.product(id));
    }

    @RequestMapping(value = "/products")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.PRODUCT_EDIT)
    public ModelAndView products_adm(String productQo) throws Exception {
        return feedback(productService.products_adm(parseModel(productQo, new ProductQo())));
    }

    @RequestMapping(value = "/outsome")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.PRODUCT_EDIT)
    public ModelAndView outSome(String ids) {
        productService.outSome(JSON.parseArray(ids, Integer.class));
        return feedback();
    }

    @RequestMapping(value = "/putsome")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.PRODUCT_EDIT)
    public ModelAndView putSome(String ids) {
        productService.putSome(JSON.parseArray(ids, Integer.class));
        return feedback();
    }

}