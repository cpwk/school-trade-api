package cn.jianchen.com.trade.api.merchant.authority;


import cn.jianchen.com.trade.common.context.Contexts;
import cn.jianchen.com.trade.common.context.SessionWrap;
import cn.jianchen.com.trade.common.exception.ErrorCode;
import cn.jianchen.com.trade.common.exception.ServiceException;

import java.util.Collections;
import java.util.List;

public class MerchantAdminContexts extends Contexts implements ErrorCode {

    public static List<String> requestProductCategorySequences() {
        MerchantAdminSessionWrapper wrapper = requireSessionWrap();
        List<String> sequences = wrapper.getMerchant().getProductCategorySequences();
        //按照正序排序
        Collections.sort(sequences);
        return sequences;
    }

    public static MerchantAdminSessionWrapper getSessionWrap() {
        SessionWrap session = getWrapper();
        if (session == null) {
            return null;
        }
        if (!(session instanceof MerchantAdminSessionWrapper)) {
            return null;
        }
        return (MerchantAdminSessionWrapper) session;
    }

    public static MerchantAdminSessionWrapper requireSessionWrap() throws ServiceException {
        MerchantAdminSessionWrapper session = getSessionWrap();
        if (session == null) {
            throw new ServiceException(ERR_SESSION_EXPIRES);
        }
        return session;
    }

    public static Integer requestMerchantId() throws ServiceException {
        Integer merchantId = sessionMerchantId();
        if (merchantId == null) {
            throw new ServiceException(ERR_SESSION_EXPIRES);
        }
        return merchantId;
    }

    public static Integer sessionMerchantId() throws ServiceException {
        MerchantAdminSessionWrapper session = getSessionWrap();
        if (session != null) {
            return session.getMerchant().getId();
        }
        return null;
    }

    public static Integer requestMerchantAdminId() throws ServiceException {
        Integer merchantAdminId = sessionMerchantAdminId();
        if (merchantAdminId == null) {
            throw new ServiceException(ERR_SESSION_EXPIRES);
        }
        return merchantAdminId;
    }

    public static Integer sessionMerchantAdminId() throws ServiceException {
        MerchantAdminSessionWrapper session = getSessionWrap();
        if (session != null) {
            return session.getMerchantAdminSession().getAdminId();
        }
        return null;
    }

}
