package com.zyx.zyxojsandbox;

import com.zyx.zyxojsandbox.model.ExecuteCodeRequest;
import com.zyx.zyxojsandbox.model.ExecuteCodeResponse;

/**
 * 模板模式
 * @author zyx
 * @version 1.0
 * @date 2024/1/11 011 10:59
 */
public abstract class JavaCodeSandboxTemplate implements CodeSandbox{

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest)  {
        
        return null;
    }
}
