package cn.jianchen.com.trade.common.controller;


import cn.jianchen.com.trade.common.util.L;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class ApiLog {

    public static void log(HttpServletRequest request, String result) {
        try {
            StringBuilder buf = new StringBuilder(request.getRequestURL());
            buf.append(", ADDR: ").append(request.getRemoteAddr());
            buf.append(", PARAMETERS: ");
            Enumeration<?> enu = request.getParameterNames();
            while (enu.hasMoreElements()) {
                String key = enu.nextElement().toString();
                Object value = request.getParameter(key);
                buf.append(key + ":" + value);
                buf.append(" ");
            }
            buf.append(", HEADERS: ");
            enu = request.getHeaderNames();
            while (enu.hasMoreElements()) {
                String key = enu.nextElement().toString();
                Object value = request.getHeader(key);
                buf.append(key + ":" + value);
                buf.append(" ");
            }
            if (result != null) {
                buf.append(", RESULT: ");
                buf.append(result);
            }
            L.warn(buf.toString());
        } catch (Throwable t) {
            L.error(t);
        }
    }

}
