package cn.jianchen.com.trade.api.merchant.repository;


import cn.jianchen.com.trade.api.merchant.model.MerchantAdmin;
import cn.jianchen.com.trade.common.reposiotry.BaseRepository;

public interface MerchantAdminRepository extends BaseRepository<MerchantAdmin, Integer> {

    MerchantAdmin findByMobile(String mobile);

}
