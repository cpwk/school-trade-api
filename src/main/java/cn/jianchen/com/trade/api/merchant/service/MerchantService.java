package cn.jianchen.com.trade.api.merchant.service;

import cn.jianchen.com.trade.api.merchant.entity.MerchantState;
import cn.jianchen.com.trade.api.merchant.model.Merchant;
import cn.jianchen.com.trade.api.merchant.model.MerchantAdmin;
import cn.jianchen.com.trade.api.merchant.model.MerchantSetting;
import cn.jianchen.com.trade.api.merchant.qo.MerchantAdminQo;
import cn.jianchen.com.trade.api.merchant.qo.MerchantQo;
import cn.jianchen.com.trade.api.merchant.qo.MerchantWrapOption;
import cn.jianchen.com.trade.api.merchant.repository.MerchantRepository;
import cn.jianchen.com.trade.api.vip.entity.Duration;
import cn.jianchen.com.trade.common.cache.CacheOptions;
import cn.jianchen.com.trade.common.cache.KvCacheFactory;
import cn.jianchen.com.trade.common.cache.KvCacheWrap;
import cn.jianchen.com.trade.common.entity.Constants;
import cn.jianchen.com.trade.common.exception.ArgumentServiceException;
import cn.jianchen.com.trade.common.exception.ErrorCode;
import cn.jianchen.com.trade.common.exception.ServiceException;
import cn.jianchen.com.trade.common.util.CollectionUtil;
import cn.jianchen.com.trade.common.util.DateUtils;
import cn.jianchen.com.trade.common.util.StringUtils;
import com.sunnysuperman.kvcache.RepositoryProvider;
import com.sunnysuperman.kvcache.converter.BeanModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MerchantService implements IMerchantService, ErrorCode {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private IMerchantAdminService merchantAdminService;

    @Autowired
    private KvCacheFactory kvCacheFactory;

    private KvCacheWrap<Integer, Merchant> merchantCache;

    @PostConstruct
    public void init() {
        merchantCache = kvCacheFactory.create(new CacheOptions("merchant", 1, DateUtils.SECOND_PER_HOUR),
                new RepositoryProvider<Integer, Merchant>() {

                    @Override
                    public Merchant findByKey(Integer id) throws Exception {
                        return merchantRepository.findById(id).orElse(null);
                    }

                    @Override
                    public Map<Integer, Merchant> findByKeys(Collection<Integer> ids) throws Exception {
                        List<Merchant> list = merchantRepository.findByIdIn((ids.stream().collect(Collectors.toList())));
                        if (CollectionUtil.isEmpty(list)) {
                            return Collections.emptyMap();
                        }
                        return list.stream().collect(Collectors.toMap(Merchant::getId, m -> m));
                    }
                }, new BeanModelConverter<>(Merchant.class));
    }

    private long parseDuration(Merchant merchant, String duration) throws ServiceException {
        Duration dur = Duration.parse(duration);
        if (dur == null || dur.forever()) {
            throw new ArgumentServiceException("Bad duration");
        }
        long now = System.currentTimeMillis();
        return dur.addDate(new Date(merchant.getValidThru() > now ? merchant.getValidThru() : now)).getTime();
    }

    private void validMerchant(Merchant merchant, boolean update) {
        String name = merchant.getName();
        if (StringUtils.isEmpty(name) || name.length() > Constants.MERCHANT_NAME_LENGTH_MAX) {
            throw new ServiceException(ERR_MERCHANT_NAME_TOO_LONG);
        }
        Merchant byName = merchantRepository.findByName(merchant.getName());
        if (!update && byName != null) {
            throw new ServiceException(ERR_MERCHANT_NAME_DUPLICATE);
        }
        if (StringUtils.isEmpty(merchant.getLogo())) {
            throw new ArgumentServiceException("logo");
        }
    }

    private void wrap(Collection<Merchant> items, MerchantWrapOption option) {
        for (Merchant merchant : items) {
            if (option.isWithAdmin()) {
                merchant.setAdmins(merchantAdminService.admins(new MerchantAdminQo(merchant.getId()), true));
            }
        }
    }

    private Merchant findById(Integer id) {
        return merchantCache.findByKey(id);
    }

    @Override
    @Transactional
    public void create(Merchant merchant, MerchantAdmin merchantAdmin) {

        validMerchant(merchant, false);

        merchant.setValidThru(System.currentTimeMillis());
        merchant.setValidThru(parseDuration(merchant, merchant.getDuration()));

        merchant.setState(MerchantState.NORMAL.value());
        merchant.setCreatedAt(System.currentTimeMillis());

        merchantRepository.save(merchant);

        // create account
        merchantAdmin.setMerchantId(merchant.getId());
        merchantAdminService.saveAdmin(merchantAdmin, true);
    }

    @Override
    public void update(Merchant merchant) {
        validMerchant(merchant, true);

        Merchant byId = getById(merchant.getId());
        byId.setName(merchant.getName());
        byId.setLogo(merchant.getLogo());
        byId.setProductCategorySequences(merchant.getProductCategorySequences());
        merchantRepository.save(byId);
        merchantCache.remove(merchant.getId());
    }

    @Override
    public Page<Merchant> items(MerchantQo qo, MerchantWrapOption options) {
        Page<Merchant> page = merchantRepository.findAll(qo);
        wrap(page.getContent(), options);
        return page;
    }

    @Override
    public Merchant getById(Integer id) {
        Merchant merchant = findById(id);
        if (merchant == null) {
            throw new ServiceException(ERR_DATA_NOT_FOUND);
        }
        return merchant;
    }

    @Override
    public void merchantState(int id, byte state) {
        Merchant merchant = getById(id);
        merchant.setState(state);
        merchantRepository.save(merchant);
    }

    @Override
    public void renew(int merchantId, String duration) throws ServiceException {
        Merchant merchant = getById(merchantId);
        merchant.setValidThru(parseDuration(merchant, duration));
        merchantRepository.save(merchant);
        merchantCache.remove(merchantId);
    }

    @Override
    public void updateSetting(int merchantId, MerchantSetting setting) {
        Merchant merchant = getById(merchantId);
        merchant.setSetting(setting);
        merchantRepository.save(merchant);
        merchantCache.remove(merchantId);
    }

    @Override
    public Merchant merchant(int id, boolean oms) {
        Merchant merchant = getById(id);

        wrap(Collections.singletonList(merchant), oms ? MerchantWrapOption.getOmsDetailInstance() : MerchantWrapOption.getDefaultInstance());

        return merchant;
    }

    @Override
    public Map<Integer, Merchant> findByIdIn(Set<Integer> ids) {
        return merchantCache.findByKeys(ids);
    }
}
