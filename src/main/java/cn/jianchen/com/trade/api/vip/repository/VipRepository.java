package cn.jianchen.com.trade.api.vip.repository;


import cn.jianchen.com.trade.api.vip.model.Vip;
import cn.jianchen.com.trade.common.reposiotry.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface VipRepository extends BaseRepository<Vip, Integer> {

    Vip findByGrade(Integer grade);

    Vip findByName(String name);

    List<Vip> findByIdIs(List<Integer> ids);

    @Transactional
    @Modifying
    @Query("update Vip set status=:status where id = :id")
    void modStatusById(Integer id, Integer status);
}