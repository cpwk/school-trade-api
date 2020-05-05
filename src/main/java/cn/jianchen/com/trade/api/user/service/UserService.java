package cn.jianchen.com.trade.api.user.service;


import cn.jianchen.com.trade.api.user.model.User;
import cn.jianchen.com.trade.api.user.model.UserConfig;
import cn.jianchen.com.trade.api.user.model.UserSession;
import cn.jianchen.com.trade.api.user.model.UserSessionWrap;
import cn.jianchen.com.trade.api.user.qo.UserQo;
import cn.jianchen.com.trade.api.user.qo.UserSessionQo;
import cn.jianchen.com.trade.api.user.repository.UserRepository;
import cn.jianchen.com.trade.api.user.repository.UserSessionRepository;
import cn.jianchen.com.trade.api.vip.repository.VipUserRepository;
import cn.jianchen.com.trade.common.code.model.CodeCache;
import cn.jianchen.com.trade.common.code.model.VCode;
import cn.jianchen.com.trade.common.code.service.VCodeService;
import cn.jianchen.com.trade.common.context.Contexts;
import cn.jianchen.com.trade.common.exception.ServiceException;
import cn.jianchen.com.trade.common.util.CollectionUtil;
import cn.jianchen.com.trade.common.util.DateUtils;
import cn.jianchen.com.trade.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static cn.jianchen.com.trade.common.exception.ErrorCode.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 **/

@Service
public class UserService implements IUserService {

    @Autowired
    private UserSessionRepository userSessionRepository;
    @Autowired
    private UserConfig userConfig;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CodeCache codeCache;
    @Autowired
    private VCodeService vCodeService;
    @Autowired
    private VipUserRepository vipUserRepository;

    public User getById(Integer userId) throws ServiceException {

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new ServiceException(ERR_DATA_NOT_FOUND);
        }

        return user;
    }

    @Override
    public UserSessionWrap signIn(String unknown, String password, VCode vCode, String ip) throws Exception {
        if (!(vCode.getCode().equals(vCodeService.getVCode(vCode.getKey()).getCode()))) {
            throw new ServiceException(ERR_VCODE_INVALID);
        }
        if (!StringUtils.validatePassword(password)) {
            throw new ServiceException(ERR_USER_PASSWORD_FORMAT);
        }
        if (!StringUtils.isChinaMobile(unknown) && !StringUtils.isEmail(unknown) && !StringUtils.validateNick(unknown)) {
            throw new ServiceException(ERR_ACCOUNT_NOT_EXIST);
        }
        User exist = null;
        if (StringUtils.isChinaMobile(unknown)) {
            exist = userRepository.findByMobile(unknown);
        } else if (StringUtils.isEmail(unknown)) {
            exist = userRepository.findByEmail(unknown);
        } else if (StringUtils.validateNick(unknown)) {
            exist = userRepository.findByNick(unknown);
        }
        if (exist == null) {
            throw new ServiceException(ERR_ACCOUNT_NOT_EXIST);
        }
        if (((StringUtils.encryptPassword(password, userConfig.getSalt())).equals(exist.getPassword())) != true) {
            throw new ServiceException(ERR_PASSWORD_INVALID);
        }
        UserSession userSession = new UserSession();
        userSession.setUserId(exist.getId());
        userSession.setToken(StringUtils.getRandStr(64));
        Long now = System.currentTimeMillis();
        userSession.setSignInAt(now);
        userSession.setExpireAt(DateUtils.addDays(now, userConfig.getSessionDays()));
        userSession.setIp(ip);
        userSessionRepository.save(userSession);
        exist.setVipUser(vipUserRepository.findByUserId(exist.getId()));
        return new UserSessionWrap(exist, userSession);
    }

    @Override
    public void signUp(User user, String key, String smsCode) throws Exception {
        if (!(codeCache.getMobile(key).equals(user.getMobile()))) {
            throw new ServiceException(ERR_USER_MOBILE_DIFFER);
        }
        if (!(smsCode.equals(codeCache.getCode(key)))) {
            throw new ServiceException(ERR_VCODE_INVALID);
        }
        if (!StringUtils.isChinaMobile(user.getMobile())) {
            throw new ServiceException(ERR_USER_MOBILE_INVALID);
        }
        if (!StringUtils.isEmail(user.getEmail())) {
            throw new ServiceException(ERR_USER_EMAIE_INVALID);
        }
        if (!StringUtils.validateNick(user.getNick())) {
            throw new ServiceException(ERR_USER_NICK_FORMAT);
        }
        if (!StringUtils.validatePassword(user.getPassword())) {
            throw new ServiceException(ERR_USER_PASSWORD_FORMAT);
        }
        if (userRepository.findByMobile(user.getMobile()) != null) {
            throw new ServiceException(ERR_USER_MOBILE_USED);
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new ServiceException(ERR_USER_EMAIE_USED);
        }

        user.setPassword(StringUtils.encryptPassword(user.getPassword(), userConfig.getSalt()));
        user.setCreatedAt(System.currentTimeMillis());
        userRepository.save(user);
    }

    @Override
    public UserSession findByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return userSessionRepository.findByToken(token);
    }

    @Override
    public String forgetPassword(String mobile, VCode vCode) throws Exception {
        if (!StringUtils.isChinaMobile(mobile)) {
            throw new ServiceException(ERR_USER_MOBILE_INVALID);
        }
        if (userRepository.findByMobile(mobile) == null) {
            throw new ServiceException(ERR_ACCOUNT_NOT_EXIST);
        }
        if (!(vCode.getCode().equals(vCodeService.getVCode(vCode.getKey()).getCode()))) {
            throw new ServiceException(ERR_VCODE_INVALID);
        }
        return mobile;
    }

    @Override
    public void resetPassword(String mobile, String password, String key, String smsCode) throws Exception {
        if (!(smsCode.equals(codeCache.getCode(key)))) {
            throw new ServiceException(ERR_VCODE_INVALID);
        }
        if (!StringUtils.validatePassword(password)) {
            throw new ServiceException(ERR_USER_PASSWORD_FORMAT);
        }
        User exits = userRepository.findByMobile(mobile);
        exits.setPassword(StringUtils.encryptPassword(password, userConfig.getSalt()));
        userRepository.save(exits);
    }

    @Override
    public void updatePassword(String mobile, String password, String newpassword, VCode vCode) throws Exception {
        if (!(vCode.getCode().equals(vCodeService.getVCode(vCode.getKey()).getCode()))) {
            throw new ServiceException(ERR_VCODE_INVALID);
        }
        if (!StringUtils.validatePassword(password) || !StringUtils.validatePassword(newpassword)) {
            throw new ServiceException(ERR_USER_PASSWORD_FORMAT);
        }
        User exist = userRepository.findByMobile(mobile);
        if (((StringUtils.encryptPassword(password, userConfig.getSalt())).equals(exist.getPassword())) != true) {
            throw new ServiceException(ERR_PASSWORD_INVALID);
        }
        exist.setPassword(StringUtils.encryptPassword(newpassword, userConfig.getSalt()));
        userRepository.save(exist);
    }

    @Override
    public Page<User> findAllUser(UserQo userQo) throws Exception {
        return userRepository.findAll(userQo);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Integer id) throws Exception {
        return getById(id);
    }

    @Override
    public Map profile() throws Exception {
        Integer userId = Contexts.requestUserId();
        User user = user(userId, true);
        return CollectionUtil.arrayAsMap("user", user);
    }

    @Override
    public User user(int id, boolean init) {
        User user = getById(id);
        if (init) {
        }
        user.setVipUser(vipUserRepository.findByUserId(id));
        return user;
    }

    @Override
    public Page<UserSession> userSession(UserSessionQo qo) throws Exception {
        return userSessionRepository.findAll(qo);
    }

    @Override
    public void save(User user) throws ServiceException {
        userRepository.save(user);
    }

    @Override
    public void modMibile(String mobile, VCode vCode) throws Exception {

        if (!(vCode.getCode().equals(vCodeService.getVCode(vCode.getKey()).getCode()))) {
            throw new ServiceException(ERR_VCODE_INVALID);
        }

        if (!StringUtils.isChinaMobile(mobile)) {
            throw new ServiceException(ERR_USER_MOBILE_INVALID);
        }

        if (userRepository.findByMobile(mobile) != null) {
            throw new ServiceException(ERR_USER_MOBILE_USED);
        }

        User user = getById(Contexts.requestUserId());

        user.setMobile(mobile);

        userRepository.save(user);
    }

    @Override
    public List<User> findByIdIn(List<Integer> ids) {
        return userRepository.findByIdIn(ids);
    }

    @Override
    public User findByMobile(String mobile) throws Exception {
        return userRepository.findByMobile(mobile);
    }
}