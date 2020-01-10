package cn.jianchen.com.trade.api.comment.service;


import cn.jianchen.com.trade.api.comment.model.Comment;
import cn.jianchen.com.trade.api.comment.qo.CommentQo;
import cn.jianchen.com.trade.api.comment.repository.CommentRepository;
import cn.jianchen.com.trade.api.orders.model.Orders;
import cn.jianchen.com.trade.api.orders.service.OrdersService;
import cn.jianchen.com.trade.api.user.model.User;
import cn.jianchen.com.trade.api.user.service.IUserService;
import cn.jianchen.com.trade.common.context.Contexts;
import cn.jianchen.com.trade.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.jianchen.com.trade.common.entity.Constants.COMMENTED;
import static cn.jianchen.com.trade.common.exception.ErrorCode.ERR_DATA_NOT_FOUND;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Service
public class CommentService implements ICommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private IUserService userService;

    public Comment getById(Integer commentId) throws ServiceException {

        Comment comment = commentRepository.findById(commentId).orElse(null);

        if (comment == null) {
            throw new ServiceException(ERR_DATA_NOT_FOUND);
        }

        return comment;
    }

    @Override
    public void remove(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void save(Comment comment) {

        Integer userId = Contexts.requestUserId();
        comment.setUserId(userId);

        Orders order = ordersService.getById(comment.getOrdersId());
        order.setStatus(COMMENTED);

        comment.setCreateAt(System.currentTimeMillis());

        commentRepository.save(comment);
    }

    @Override
    public Comment findComment(Integer id) {
        return getById(id);
    }

    @Override
    public Page<Comment> comments(CommentQo qo) {

        Page<Comment> comments = commentRepository.findAll(qo);

        List<Integer> ordersIds = new ArrayList<>(comments.getSize());
        List<Integer> usersIds = new ArrayList<>(comments.getSize());

        for (Comment comment : comments) {
            ordersIds.add(comment.getOrdersId());
            usersIds.add(comment.getUserId());
        }

        List<Orders> orders = ordersService.findByIdIs(ordersIds);
        List<User> users = userService.findByIdIs(usersIds);

        Map<Integer, Orders> ordersMap = new HashMap<>(orders.size());
        Map<Integer, User> userMap = new HashMap<>(users.size());

        for (Orders o : orders) {
            ordersMap.put(o.getId(), o);
        }
        for (User user : users) {
            userMap.put(user.getId(), user);
        }

        for (Comment comment : comments) {
            comment.setOrders(ordersMap.get(comment.getOrdersId()));
            comment.setUser(userMap.get(comment.getUserId()));
        }

        return comments;
    }
}
