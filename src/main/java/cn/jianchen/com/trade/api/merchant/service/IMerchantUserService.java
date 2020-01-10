package cn.jianchen.com.trade.api.merchant.service;

import cn.jianchen.com.trade.api.merchant.model.Merchant;
import cn.jianchen.com.trade.api.merchant.qo.MerchantUserQo;
import org.springframework.data.domain.Page;

public interface IMerchantUserService {

    Page<Merchant> merchants(MerchantUserQo qo);
}
