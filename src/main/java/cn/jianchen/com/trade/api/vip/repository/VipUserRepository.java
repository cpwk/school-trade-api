package cn.jianchen.com.trade.api.vip.repository;


import cn.jianchen.com.trade.api.vip.model.VipUser;
import cn.jianchen.com.trade.common.reposiotry.BaseRepository;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface VipUserRepository extends BaseRepository<VipUser, Integer> {

    VipUser findByUserId(Integer userId);

}