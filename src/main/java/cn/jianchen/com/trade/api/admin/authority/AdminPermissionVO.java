package cn.jianchen.com.trade.api.admin.authority;


import cn.jianchen.com.trade.common.authority.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class AdminPermissionVO extends Permission {

    private static List<Permission> list = null;

    public static List<Permission> initPermissions() {
        if (list == null) {
            list = new ArrayList<>();
            for (AdminPermission p : AdminPermission.values()) {
                list.add(new Permission(p.name(), p.getVal(), p.getLevel()));
            }
        }
        return list;
    }

    public static List<Permission> initPermissionsByPs(List<String> ps) {
        List<Permission> list = initPermissions();
        List<Permission> result = new ArrayList<Permission>();
        if (ps.size() > 0) {
            for (String s : ps) {
                for (Permission p : list) {
                    if (s.equals(p.getCode()))
                        result.add(p);
                }
            }
        }
        return result;
    }
}