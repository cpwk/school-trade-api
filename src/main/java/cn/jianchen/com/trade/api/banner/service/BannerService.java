package cn.jianchen.com.trade.api.banner.service;


import cn.jianchen.com.trade.api.banner.model.Banner;
import cn.jianchen.com.trade.api.banner.qo.BannerQo;
import cn.jianchen.com.trade.api.banner.repository.IBannerRepository;
import cn.jianchen.com.trade.common.exception.ServiceException;
import cn.jianchen.com.trade.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cn.jianchen.com.trade.common.entity.Constants.STOP_USED;
import static cn.jianchen.com.trade.common.entity.Constants.USED;
import static cn.jianchen.com.trade.common.exception.ErrorCode.ERR_BANNER_IMG_NULL;
import static cn.jianchen.com.trade.common.exception.ErrorCode.ERR_DATA_NOT_FOUND;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Service
public class BannerService implements IBannerService {

    @Autowired
    private IBannerRepository bannerRepository;

    public Banner getById(Integer bannerId) throws ServiceException {

        Banner banner = bannerRepository.findById(bannerId).orElse(null);

        if (banner == null) {
            throw new ServiceException(ERR_DATA_NOT_FOUND);
        }

        return banner;
    }

    @Override
    public List<Banner> banners(BannerQo qo, boolean admin) {
        if (!admin) {
            qo.setStatus(USED);
        }
        return bannerRepository.findAll(qo);
    }

    @Override
    public Banner banner(int id) {
        return getById(id);
    }

    @Override
    public void save(Banner banner) throws ServiceException {
        if (StringUtils.isEmpty(banner.getImg())) {
            throw new ServiceException(ERR_BANNER_IMG_NULL);
        }
        bannerRepository.save(banner);
    }

    @Override
    public void remove(int id) {
        bannerRepository.deleteById(id);
    }

    @Override
    public void outSome(List<Integer> ids) {

        List<Banner> banners = new ArrayList<>(ids.size());

        for (Integer id : ids) {
            Banner exist = getById(id);
            exist.setStatus(STOP_USED);
            banners.add(exist);
        }

        bannerRepository.saveAll(banners);

    }

    @Override
    public void putSome(List<Integer> ids) {

        List<Banner> banners = new ArrayList<>(ids.size());

        for (Integer id : ids) {
            Banner exist = getById(id);
            exist.setStatus(USED);
            banners.add(exist);
        }
        bannerRepository.saveAll(banners);

    }
}
