package cn.jianchen.com.trade.common.exception;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class RuntimeServiceException extends ServiceException {
    private static final long serialVersionUID = 550893169395750576L;

    public RuntimeServiceException() {
        super(ErrorCode.ERR_UNKNOWN_ERROR);
    }

    public RuntimeServiceException(Throwable t) {
        this();
        this.initCause(t);
    }

    public RuntimeServiceException(String s) {
        this();
        this.initCause(new RuntimeException(s));
    }

}
