package com.zyx.zyxojsandbox.security;

import java.security.Permission;

/**
 * @author zyx
 * @version 1.0
 * @date 2024/1/9 009 14:14
 */
public class MySecurityManager extends SecurityManager {

    /**
     * 检查所有权限
     * @param perm
     */
    @Override
    public void checkPermission(Permission perm) {
        super.checkPermission(perm);
    }

    @Override
    public void checkExec(String cmd) {
        super.checkExec(cmd);
    }

    @Override
    public void checkRead(String file) {
        super.checkRead(file);
    }

    @Override
    public void checkWrite(String file) {
        super.checkWrite(file);
    }

    @Override
    public void checkDelete(String file) {
        super.checkDelete(file);
    }

    @Override
    public void checkConnect(String host, int port) {
        super.checkConnect(host, port);
    }
}
