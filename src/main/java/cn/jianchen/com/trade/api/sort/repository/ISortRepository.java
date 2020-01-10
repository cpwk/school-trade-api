package cn.jianchen.com.trade.api.sort.repository;


import cn.jianchen.com.trade.api.sort.model.Sort;
import cn.jianchen.com.trade.common.reposiotry.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface ISortRepository extends BaseRepository<Sort, Integer> {

    @Transactional
    @Modifying
    @Query("update Sort set status= :status where sequence like CONCAT(:sequence,'%')")
    void status(@Param(value = "status") Byte status, @Param(value = "sequence") String sequence);
}