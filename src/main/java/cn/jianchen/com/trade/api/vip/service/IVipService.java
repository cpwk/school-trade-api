package cn.jianchen.com.trade.api.vip.service;


import cn.jianchen.com.trade.api.vip.model.Vip;
import cn.jianchen.com.trade.api.vip.model.VipUser;
import cn.jianchen.com.trade.api.vip.qo.VipQo;
import cn.jianchen.com.trade.api.vip.qo.VipUserQo;
import cn.jianchen.com.trade.common.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface IVipService {

    void save(Vip vip) throws ServiceException;

    void remove(Integer id) throws Exception;

    Vip vip(Integer id);

    List<Vip> vips(VipQo qo);

    Vip search(Integer grade);

    void modStatus(Integer id, Integer status);

    Page<VipUser> vipUsers(VipUserQo qo);

    void renew(Integer id, String duration, String remark); //续费

    void admNewVipUser(VipUser vipUser, String phone) throws Exception;

    void save(VipUser vipUser) throws ServiceException;

    void expire(Integer id) throws Exception;

    VipUser vipUser(Integer id);

    VipUser userVip();

    VipUser profile();

    void newVip(Integer id, Integer sno); //用户开通

    void userRenew(Integer id, Integer sno); //续费

}
