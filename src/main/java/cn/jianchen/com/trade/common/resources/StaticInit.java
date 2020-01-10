package cn.jianchen.com.trade.common.resources;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Target({ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface StaticInit {

}
