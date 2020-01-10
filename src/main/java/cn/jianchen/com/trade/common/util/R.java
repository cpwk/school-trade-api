package cn.jianchen.com.trade.common.util;


import com.sunnysuperman.commons.util.FileUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class R {

    public static InputStream getStream(String path) {
        return R.class.getResourceAsStream("/" + path);
    }

    public static String getString(String path) {
        try {
            return FileUtil.read(getStream(path));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static byte[] getBytes(String path) {
        try {
            return FileUtil.readAsByteArray(getStream(path));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}