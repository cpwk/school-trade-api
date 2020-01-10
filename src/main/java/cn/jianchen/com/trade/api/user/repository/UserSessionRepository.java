package cn.jianchen.com.trade.api.user.repository;


import cn.jianchen.com.trade.api.user.model.UserSession;
import cn.jianchen.com.trade.common.reposiotry.BaseRepository;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-09 10:34
 **/

public interface UserSessionRepository extends BaseRepository<UserSession, Integer> {
    UserSession findByToken(String token);
}
