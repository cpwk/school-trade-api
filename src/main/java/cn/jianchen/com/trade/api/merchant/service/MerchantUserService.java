package cn.jianchen.com.trade.api.merchant.service;

import cn.jianchen.com.trade.api.merchant.model.Merchant;
import cn.jianchen.com.trade.api.merchant.qo.MerchantUserQo;
import cn.jianchen.com.trade.api.merchant.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class MerchantUserService implements IMerchantUserService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public Page<Merchant> merchants(MerchantUserQo qo) {
        return merchantRepository.findAll(qo);
    }
}
