package cn.jianchen.com.trade.common.reposiotry;


import cn.jianchen.com.trade.common.reposiotry.support.DataQueryObject;
import cn.jianchen.com.trade.common.reposiotry.support.DataQueryObjectPage;
import cn.jianchen.com.trade.common.reposiotry.support.DataQueryObjectSort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@NoRepositoryBean
@Transactional(readOnly = true, rollbackFor = Exception.class)
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    List<T> findAll(DataQueryObject query);

    Page<T> findAll(DataQueryObject query, Pageable page);

    Page<T> findAll(DataQueryObjectPage dataQueryObjectpage);

    List<T> findAll(DataQueryObject dataQueryObject, Sort sort);

    List<T> findAll(DataQueryObjectSort dataQueryObjectSort);
}