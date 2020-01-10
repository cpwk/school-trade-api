package cn.jianchen.com.trade.api.merchant.service;

import cn.jianchen.com.trade.api.merchant.model.Merchant;
import cn.jianchen.com.trade.api.merchant.model.MerchantAdmin;
import cn.jianchen.com.trade.api.merchant.model.MerchantSetting;
import cn.jianchen.com.trade.api.merchant.qo.MerchantQo;
import cn.jianchen.com.trade.api.merchant.qo.MerchantWrapOption;
import cn.jianchen.com.trade.common.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.Set;

public interface IMerchantService {

    void create(Merchant merchant, MerchantAdmin merchantAdmin);

    void update(Merchant merchant);

    Page<Merchant> items(MerchantQo qo, MerchantWrapOption options);

    Merchant getById(Integer id);

    void merchantState(int id, byte state);

    void renew(int merchantId, String duration) throws ServiceException;

    void updateSetting(int merchantId, MerchantSetting setting);

    Merchant merchant(int id, boolean oms);

    Map<Integer, Merchant> findByIdIn(Set<Integer> ids);

}
