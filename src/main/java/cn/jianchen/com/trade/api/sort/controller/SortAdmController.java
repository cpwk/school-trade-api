package cn.jianchen.com.trade.api.sort.controller;


import cn.jianchen.com.trade.api.admin.authority.AdminPermission;
import cn.jianchen.com.trade.api.sort.model.Sort;
import cn.jianchen.com.trade.api.sort.service.ISortService;
import cn.jianchen.com.trade.common.authority.AdminType;
import cn.jianchen.com.trade.common.authority.RequiredPermission;
import cn.jianchen.com.trade.common.controller.BaseController;
import cn.jianchen.com.trade.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Controller
@RequestMapping(value = "/adm/sort")
public class SortAdmController extends BaseController {
    @Autowired
    private ISortService iSortService;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.SORT_EDIT)
    public ModelAndView save(String sort) throws Exception {
        iSortService.save(parseModel(sort, new Sort()));
        return feedback(null);
    }

    @RequestMapping(value = "/sorts")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.SORT_EDIT)
    public ModelAndView sorts() {
        return feedback(iSortService.sorts(true));
    }

    @RequestMapping(value = "/status")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.SORT_EDIT)
    public ModelAndView statusExamQuestionType(Integer id, Byte status) throws ServiceException {
        iSortService.status(id, status);
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.SORT_EDIT)
    public ModelAndView removeExamQuestionType(Integer id) throws ServiceException {
        iSortService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/sort")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.SORT_EDIT)
    public ModelAndView sort(Integer id) throws Exception {
        return feedback(iSortService.sort(id));
    }

}
