package cn.jianchen.com.trade.api.comment.controller;


import cn.jianchen.com.trade.api.admin.authority.AdminPermission;
import cn.jianchen.com.trade.api.comment.model.Comment;
import cn.jianchen.com.trade.api.comment.qo.CommentQo;
import cn.jianchen.com.trade.api.comment.service.CommentService;
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
@RequestMapping(value = "/adm/comment")
public class CommentController extends BaseController {
    @Autowired
    private CommentService commentService;

    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COMMENT_EDIT)
    @RequestMapping(value = "/save")
    public ModelAndView save(String comment) throws Exception {
        commentService.save(parseModel(comment, new Comment()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.NONE, adminPermission = AdminPermission.COMMENT_EDIT)
    public ModelAndView remove(Integer id) throws Exception {
        commentService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/comment")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COMMENT_EDIT)
    public ModelAndView comment(Integer id) throws Exception {
        return feedback(commentService.findComment(id));
    }

    @RequestMapping(value = "/comments")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COMMENT_EDIT)
    public ModelAndView comments(String commentQo) throws Exception {
        return feedback(commentService.comments(parseModel(commentQo, new CommentQo())));
    }
}
