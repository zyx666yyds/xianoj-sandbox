package com.zyx.zyxojsandbox;

import com.zyx.zyxojsandbox.model.ExecuteCodeRequest;
import com.zyx.zyxojsandbox.model.ExecuteCodeResponse;
import org.springframework.stereotype.Component;

/**
 * java原生代码沙箱
 * @author zyx
 * @version 1.0
 * @date 2024/1/11 011 11:35
 */
@Component
public class JavaNativeCodeSandbox extends JavaCodeSandboxTemplate {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        return super.executeCode(executeCodeRequest);
    }
}
