package cn.jianchen.com.trade.api.sort.controller;


import cn.jianchen.com.trade.api.sort.service.ISortService;
import cn.jianchen.com.trade.common.authority.AdminType;
import cn.jianchen.com.trade.common.authority.RequiredPermission;
import cn.jianchen.com.trade.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Controller
@RequestMapping(value = "/usr/sort")
public class SortUsrController extends BaseController {
    @Autowired
    private ISortService iSortService;

    @RequestMapping(value = "/sorts")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView sorts() {
        return feedback(iSortService.sorts(true));
    }

}
