package cn.jianchen.com.trade.common.util;

import com.sunnysuperman.commons.model.ObjectId;

import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public final class UUIDCreatorFactory {

    private static final UUIDCreator DEFAULT_CREATOR = UUIDCreatorFactory.get();

    public static UUIDCreator get() {
        return new UUIDCreator();
    }

    public static UUIDCreator getDefault() {
        return DEFAULT_CREATOR;
    }

    public static class UUIDCreator {
        private final AtomicInteger counter = new AtomicInteger(new SecureRandom().nextInt());

        public String create() {
            return new ObjectId(counter).toHexString();
        }
    }

}
