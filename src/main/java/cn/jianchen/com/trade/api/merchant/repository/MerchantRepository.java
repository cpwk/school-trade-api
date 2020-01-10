package cn.jianchen.com.trade.api.merchant.repository;


import cn.jianchen.com.trade.api.merchant.model.Merchant;
import cn.jianchen.com.trade.common.reposiotry.BaseRepository;

import java.util.List;

public interface MerchantRepository extends BaseRepository<Merchant, Integer> {

    Merchant findByName(String name);

    List<Merchant> findByIdIn(List<Integer> ids);

}
