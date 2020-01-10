package cn.jianchen.com.trade.api.user.service;


import cn.jianchen.com.trade.api.user.model.User;
import cn.jianchen.com.trade.api.user.model.UserSession;
import cn.jianchen.com.trade.api.user.model.UserSessionWrap;
import cn.jianchen.com.trade.api.user.qo.UserQo;
import cn.jianchen.com.trade.api.user.qo.UserSessionQo;
import cn.jianchen.com.trade.common.code.model.VCode;
import cn.jianchen.com.trade.common.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:08
 **/

public interface IUserService {

    void signUp(User user, String key, String smsCode) throws Exception;

    UserSessionWrap signIn(String unknown, String password, VCode vCode, String ip) throws Exception;

    UserSession findByToken(String token);

    String forgetPassword(String mobile, VCode vCode) throws Exception;

    void resetPassword(String mobile, String password, String key, String smsCode) throws Exception;

    void updatePassword(String mobile, String password, String newpassword, VCode vCode) throws Exception;

    void deleteById(Integer id) throws Exception;

    User findById(Integer id) throws Exception;

    Page<User> findAllUser(UserQo userQo) throws Exception;

    Map profile() throws Exception;

    User user(int id, boolean init);

    Page<UserSession> userSession(UserSessionQo qo) throws Exception;

    void save(User user) throws ServiceException;

    void modMibile(String mobile, VCode vCode) throws Exception;

    List<User> findByIdIs(List<Integer> ids);

    User findByMobile(String mobile) throws Exception;


}
