package cn.jianchen.com.trade.api.comment.service;


import cn.jianchen.com.trade.api.comment.model.Comment;
import cn.jianchen.com.trade.api.comment.qo.CommentQo;
import org.springframework.data.domain.Page;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface ICommentService {
    void remove(Integer id);

    void save(Comment comment);

    Comment findComment(Integer id);

    Page<Comment> comments(CommentQo qo);
}
