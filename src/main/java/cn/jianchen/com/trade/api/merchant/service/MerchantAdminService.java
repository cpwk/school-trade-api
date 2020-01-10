package cn.jianchen.com.trade.api.merchant.service;

import cn.jianchen.com.trade.api.merchant.authority.MerchantAdminContexts;
import cn.jianchen.com.trade.api.merchant.authority.MerchantAdminSessionWrapper;
import cn.jianchen.com.trade.api.merchant.model.Merchant;
import cn.jianchen.com.trade.api.merchant.model.MerchantAdmin;
import cn.jianchen.com.trade.api.merchant.model.MerchantAdminSession;
import cn.jianchen.com.trade.api.merchant.qo.MerchantAdminQo;
import cn.jianchen.com.trade.api.merchant.qo.MerchantAdminSessionQo;
import cn.jianchen.com.trade.api.merchant.repository.MerchantAdminRepository;
import cn.jianchen.com.trade.api.merchant.repository.MerchantAdminSessionRepository;
import cn.jianchen.com.trade.common.cache.CacheOptions;
import cn.jianchen.com.trade.common.cache.KvCacheFactory;
import cn.jianchen.com.trade.common.cache.KvCacheWrap;
import cn.jianchen.com.trade.common.context.Context;
import cn.jianchen.com.trade.common.context.Contexts;
import cn.jianchen.com.trade.common.exception.ArgumentServiceException;
import cn.jianchen.com.trade.common.exception.ErrorCode;
import cn.jianchen.com.trade.common.exception.ServiceException;
import cn.jianchen.com.trade.common.util.CollectionUtil;
import cn.jianchen.com.trade.common.util.DateUtils;
import cn.jianchen.com.trade.common.util.StringUtils;
import com.sunnysuperman.kvcache.RepositoryProvider;
import com.sunnysuperman.kvcache.converter.BeanModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static cn.jianchen.com.trade.common.entity.Constants.STATUS_OK;
import static cn.jianchen.com.trade.common.entity.Constants.ZERO_INT;

@Service
public class MerchantAdminService implements IMerchantAdminService, ErrorCode {

    @Value("${merchant-admin.salt}")
    private String salt;
    @Value("${merchant-admin.session-days}")
    private Integer sessionDays;

    @Autowired
    private MerchantAdminRepository merchantAdminRepository;

    @Autowired
    private MerchantAdminSessionRepository merchantAdminSessionRepository;

    @Autowired
    private IMerchantAdminService merchantAdminService;

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private KvCacheFactory kvCacheFactory;

    private KvCacheWrap<String, MerchantAdminSessionWrapper> sessionCache;

    public MerchantAdmin getById(Integer merchantAdminId) throws ServiceException {

        MerchantAdmin merchantAdmin = merchantAdminRepository.findById(merchantAdminId).orElse(null);

        if (merchantAdmin == null) {
            throw new ServiceException(ERR_DATA_NOT_FOUND);
        }

        return merchantAdmin;
    }

    @PostConstruct
    public void init() {
        sessionCache = kvCacheFactory.create(new CacheOptions("merchant_admin_session", 1, (int) (sessionDays * DateUtils
                        .SECOND_PER_HOUR)),
                new RepositoryProvider<String, MerchantAdminSessionWrapper>() {

                    @Override
                    public MerchantAdminSessionWrapper findByKey(String key) throws ServiceException {
                        MerchantAdminSession merchantAdminSession = merchantAdminSessionRepository.findByToken(key);
                        if (merchantAdminSession == null || merchantAdminSession.getExpireAt() < System.currentTimeMillis()) {
                            throw new ServiceException(ERR_SESSION_EXPIRES);
                        }
                        MerchantAdmin merchantAdmin = merchantAdminService.admin(merchantAdminSession.getAdminId(), false);
                        if (merchantAdmin.getId() == null) {
                            throw new ServiceException(ERR_ACCOUNT_NOT_EXIST);
                        }
                        Merchant merchant = merchantService.getById(merchantAdmin.getMerchantId());
                        if (merchant.getId() == null) {
                            throw new ServiceException(ERR_ACCOUNT_NOT_EXIST);
                        }
                        return new MerchantAdminSessionWrapper(merchant, merchantAdmin, merchantAdminSession);
                    }

                    @Override
                    public Map<String, MerchantAdminSessionWrapper> findByKeys(Collection<String> ids) throws
                            ServiceException {
                        throw new UnsupportedOperationException("findByKeys");
                    }

                }, new BeanModelConverter<>(MerchantAdminSessionWrapper.class));
    }


    @Override
    public List<MerchantAdmin> admins(MerchantAdminQo qo, boolean oms) {

        if (!oms) {
            qo.setMerchantId(MerchantAdminContexts.requestMerchantId());
        }

        return merchantAdminRepository.findAll(qo);
    }

    @Override
    public void saveAdmin(MerchantAdmin admin, boolean oms) throws ServiceException {

        MerchantAdmin byMobile = merchantAdminRepository.findByMobile(admin.getMobile());
        if (!oms) {
            admin.setMerchantId(MerchantAdminContexts.requestMerchantId());
        } else if (admin.getMerchantId() == null) {
            throw new ArgumentServiceException("merchantId");
        }
        if (admin.getId() != null && admin.getId() > ZERO_INT) {
            if (byMobile == null)
                throw new ServiceException(ERR_MERCHANT_ADMIN_NOUSER);

            byMobile.setName(admin.getName());
            if (StringUtils.isNotEmpty(admin.getPassword()))
                byMobile.setPassword(StringUtils.encryptPassword(admin.getPassword(), salt));

            merchantAdminRepository.save(byMobile);
        } else {
            if (byMobile != null) {
                throw new ServiceException(ERR_MERCHANT_ADMIN_DUPLICATE);
            }
            admin.setPassword(StringUtils.encryptPassword(admin.getPassword(), salt));
            admin.setStatus(STATUS_OK);
            admin.setCreatedAt(new Date().getTime());
            merchantAdminRepository.save(admin);
        }

    }

    @Override
    public void removeAdmin(int id) {
        merchantAdminRepository.deleteById(id);
    }

    @Override
    public MerchantAdmin admin(int id, boolean init) {

        MerchantAdmin merchantAdmin = getById(id);

        if (init) {
        }

        return merchantAdmin;
    }

    @Override
    public MerchantAdmin findByMobile(String mobile) {
        return merchantAdminRepository.findByMobile(mobile);
    }

    @Override
    public void adminStatus(int id, byte status) {
        MerchantAdmin merchantAdmin = admin(id, false);
        merchantAdmin.setStatus(status);
        merchantAdminRepository.save(merchantAdmin);
    }

    @Override
    public void updatePassword(int id, String password) throws ServiceException {
        MerchantAdmin merchantAdmin = admin(id, false);
        merchantAdmin.setPassword(StringUtils.encryptPassword(password, salt));
        merchantAdminRepository.save(merchantAdmin);
    }

    @Override
    public void updateMyPassword(String password, String oldPassword) throws ServiceException {

        MerchantAdmin merchantAdmin = getById(MerchantAdminContexts.requestMerchantAdminId());

        if (!merchantAdmin.getPassword().equals(StringUtils.encryptPassword(oldPassword, salt))) {
            throw new ServiceException(ERR_PASSWORD_INVALID);
        }
        merchantAdmin.setPassword(StringUtils.encryptPassword(password, salt));

        merchantAdminRepository.save(merchantAdmin);
    }

    @Override
    public Page<MerchantAdminSession> adminSessions(MerchantAdminSessionQo qo) {
        return merchantAdminSessionRepository.findAll(qo);
    }

    @Override
    public Map signin(MerchantAdmin admin, String ip) throws ServiceException {
        MerchantAdmin byMobile = findByMobile(admin.getMobile());

        if (byMobile == null)
            throw new ServiceException(ERR_ACCOUNT_NOT_EXIST);

        if (byMobile.getPassword().equals(StringUtils.encryptPassword(admin.getPassword(), salt))) {
            if (byMobile.getStatus() == STATUS_OK) {

                Merchant merchant = merchantService.getById(byMobile.getMerchantId());

                if (merchant.getState() == STATUS_OK) {

                    MerchantAdminSession session = saveMerchantAdminSession(byMobile, ip);
                    MerchantAdminSessionWrapper wrap = new MerchantAdminSessionWrapper(merchant, byMobile, session);

                    Context context = Contexts.get();
                    context.setSession(wrap);

                    byMobile.setSigninAt(new Date().getTime());

                    merchantAdminRepository.save(byMobile);

                    return CollectionUtil.arrayAsMap("admin", byMobile, "session", session);
                } else {
                    throw new ServiceException(ERR_ACCOUNT_FORBID);
                }
            } else {
                throw new ServiceException(ERR_ACCOUNT_FORBID);
            }
        } else {
            throw new ServiceException(ERR_PASSWORD_INVALID);
        }
    }


    private MerchantAdminSession saveMerchantAdminSession(MerchantAdmin admin, String ip) {

        Long now = System.currentTimeMillis();

        MerchantAdminSession session = new MerchantAdminSession();
        session.setAdminId(admin.getId());
        session.setName(admin.getName());
        session.setToken(StringUtils.randomAlphanumeric(64));
        session.setIp(ip);
        session.setSigninAt(now);
        session.setExpireAt(now + sessionDays * DateUtils.MILLIS_PER_DAY);
        merchantAdminSessionRepository.save(session);
        return session;
    }


    @Override
    public Map profile() {
        Merchant merchant = merchantService.getById(MerchantAdminContexts.requestMerchantId());
        MerchantAdmin admin = admin(MerchantAdminContexts.requestMerchantAdminId(), false);
        return CollectionUtil.arrayAsMap("merchant", merchant, "admin", admin);
    }

    @Override
    public MerchantAdminSessionWrapper findByToken(String token) {
        return sessionCache.findByKey(token);
    }
}
