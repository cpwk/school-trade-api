package cn.jianchen.com.trade.api.merchant.repository;


import cn.jianchen.com.trade.api.merchant.model.MerchantAdminSession;
import cn.jianchen.com.trade.common.reposiotry.BaseRepository;

public interface MerchantAdminSessionRepository extends BaseRepository<MerchantAdminSession, Integer> {

    MerchantAdminSession findByToken(String token);

}