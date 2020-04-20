package cn.jianchen.com.trade.common.entity;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class Constants {

    //通用常量
    public static final byte STATUS_OK = 1;// 默认
    public static final byte STATUS_HALT = 2;// 删除、停用、取消

    public static final int STATUS_OK_INT = 1;// 默认

    public static int PAGESIZE_MIN = 10;
    public static int PAGESIZE_MED = 20;
    public static int PAGESIZE_MAX = 50;
    public static int PAGESIZE_INF = 10000;
    public static int SESSION_EXPIRE_DAYS = 2;
    public static int CACHE_REDIS_EXPIRE = 3600 * 48;

    public static int ZERO_INT = 0;
    public static Byte ZERO_BYTE = 0;

    // 权限操作级别
    public static String LEVEL_PRIMARY = "blue";
    public static String LEVEL_IMPORTANT = "red";
    public static String LEVEL_WARNING = "orange";

    //商户
    public static int MERCHANT_NAME_LENGTH_MAX = 20;

    //地址
    public static Byte IS_DEF = 1;
    public static Byte NOT_DEF = 2;

    //超级管理员ID
    public static Integer SUPER_ADMIN_ID = 1;

    //Banner状态
    public static Byte USED = 1;
    public static Byte STOP_USED = 2;

    //订单状态
    public static Byte WAIT_PAY = 1;
    public static Byte WAIT_SURE_PRODUCT = 2;
    public static Byte WAIT_COMMENT = 3;
    public static Byte COMMENTED = 4;

    //用户已领优惠券状态
    public static Byte EXPIRED = 2;
    public static Byte USER_ED = 3;

    //商品上架状态
    public static Byte PRODUCT_USED = 1;
    public static Byte PRODUCT_STOP_USED = 2;

    //商品审核状态
    public static Byte WAIT_DO = 1;
    public static Byte PASS = 2;
    public static Byte PASS_NOT = 3;

    //优惠券状态
    public static Byte WAIT_USER = 1;


}
