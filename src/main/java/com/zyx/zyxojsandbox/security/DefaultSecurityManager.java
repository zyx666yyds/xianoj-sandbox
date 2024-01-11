package com.zyx.zyxojsandbox.security;

import java.security.Permission;

/**
 * 默认安全管理器
 * @author zyx
 * @version 1.0
 * @date 2024/1/9 009 13:52
 */
public class DefaultSecurityManager extends SecurityManager{

    @Override
    public void checkPermission(Permission perm) {
        System.out.println("默认不做限制");
        System.out.println(perm);
        //super.checkPermission(perm);
    }
}
