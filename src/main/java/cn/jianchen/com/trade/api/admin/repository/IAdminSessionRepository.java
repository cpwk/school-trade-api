package cn.jianchen.com.trade.api.admin.repository;


import cn.jianchen.com.trade.api.admin.model.AdminSession;
import cn.jianchen.com.trade.common.reposiotry.BaseRepository;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface IAdminSessionRepository extends BaseRepository<AdminSession, Integer> {

    AdminSession findByToken(String token);

}