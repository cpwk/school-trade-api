package cn.jianchen.com.trade.api.user.repository;


import cn.jianchen.com.trade.api.user.model.User;
import cn.jianchen.com.trade.common.reposiotry.BaseRepository;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:08
 **/

public interface UserRepository extends BaseRepository<User, Integer> {

    User findByMobile(String mobile) throws Exception;

    User findByEmail(String emial) throws Exception;

    User findByNick(String nick) throws Exception;

    List<User> findByIdIs(List<Integer> ids);

}
