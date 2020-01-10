package cn.jianchen.com.trade.common.exception;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class ArgumentServiceException extends ServiceException {
    private static final long serialVersionUID = -7398899188917026294L;

    public ArgumentServiceException(String key, Serializable value) {
        super(ErrorCode.ERR_ILLEGAL_ARGUMENT);
        Map<String, Object> errorData = new HashMap<String, Object>(2);
        errorData.put("key", key);
        errorData.put("value", value);
        this.setErrorData(errorData);
    }

    public ArgumentServiceException(String key) {
        this(key, null);
    }
}
