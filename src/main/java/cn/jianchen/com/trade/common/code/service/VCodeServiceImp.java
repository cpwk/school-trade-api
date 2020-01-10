package cn.jianchen.com.trade.common.code.service;


import cn.jianchen.com.trade.common.cache.CacheOptions;
import cn.jianchen.com.trade.common.cache.KvCacheFactory;
import cn.jianchen.com.trade.common.cache.KvCacheWrap;
import cn.jianchen.com.trade.common.code.model.VCode;
import cn.jianchen.com.trade.common.exception.ServiceException;
import com.sunnysuperman.kvcache.RepositoryProvider;
import com.sunnysuperman.kvcache.converter.BeanModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;

import static cn.jianchen.com.trade.common.exception.ErrorCode.ERR_VCODE_OVERTIME;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * <p>
 * 类名称:VCodeServiceImp
 * 类描述:图片验证码服务层接口其中一个具体实现类
 **/

@Service
public class VCodeServiceImp implements VCodeService {

    @Autowired
    private KvCacheFactory kvCacheFactory;
    private KvCacheWrap<Long, VCode> vCodeCache;

    @PostConstruct
    public void init() {
        vCodeCache = kvCacheFactory.create(new CacheOptions("vcode", 1, 300),
                new RepositoryProvider<Long, VCode>() {
                    @Override
                    public VCode findByKey(Long aLong) throws Exception {
                        throw new Exception();
                    }

                    @Override
                    public Map<Long, VCode> findByKeys(Collection<Long> collection) throws Exception {
                        throw new UnsupportedOperationException("findByKeys");
                    }
                }, new BeanModelConverter<>(VCode.class));
    }

    @Override
    public void saveVCode(VCode vCode) {
        vCodeCache.save(vCode.getKey(), vCode);
    }

    @Override
    public VCode getVCode(Long key) throws Exception {
        try {
            return vCodeCache.findByKey(key);
        } catch (Exception e) {
            throw new ServiceException(ERR_VCODE_OVERTIME);
        }
    }
}
