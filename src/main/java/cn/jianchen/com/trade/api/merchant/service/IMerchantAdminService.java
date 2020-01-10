package cn.jianchen.com.trade.api.merchant.service;

import cn.jianchen.com.trade.api.merchant.authority.MerchantAdminSessionWrapper;
import cn.jianchen.com.trade.api.merchant.model.MerchantAdmin;
import cn.jianchen.com.trade.api.merchant.model.MerchantAdminSession;
import cn.jianchen.com.trade.api.merchant.qo.MerchantAdminQo;
import cn.jianchen.com.trade.api.merchant.qo.MerchantAdminSessionQo;
import cn.jianchen.com.trade.common.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public interface IMerchantAdminService {

    // admin

    List<MerchantAdmin> admins(MerchantAdminQo qo, boolean oms);

    void saveAdmin(MerchantAdmin admin, boolean oms) throws ServiceException;

    void removeAdmin(int id);

    MerchantAdmin admin(int id, boolean init);

    MerchantAdmin findByMobile(String mobile);

    void adminStatus(int id, byte status);

    void updatePassword(int id, String password) throws ServiceException;

    void updateMyPassword(String password, String oldPassword) throws ServiceException;

    Page<MerchantAdminSession> adminSessions(MerchantAdminSessionQo qo);

    Map profile();

    MerchantAdminSessionWrapper findByToken(String token);

    // signin
    Map signin(MerchantAdmin admin, String ip) throws ServiceException;

}
